package cn.xyz.chaos.mvc.shiro.session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;

import org.springside.modules.nosql.redis.JedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.exceptions.JedisException;

/**
 * cn.xyz.chaos.mvc.shiro.session 使用Redis为数据源的SessionDAO<br/>
 * 继承了CachingSessionDAO，可使用缓存来保存从Redis读取的Session。<br/>
 * 使用Redis的hash结构存储session的attribute，其中field=sessionId存放SimpleSession的基本信息
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/11/3 18:38.
 */
public class RedisCacheSessionDAO extends CachingSessionDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheSessionDAO.class);

    private JedisTemplate jedisTemplate;

    private byte[] prefix;

    public RedisCacheSessionDAO(JedisTemplate jedisTemplate, String prefix) {
        this.jedisTemplate = jedisTemplate;
        this.prefix = prefix.concat(":").getBytes();
    }

    /**
     * 只有在getCacheManager为Null 的情况下才允许设置
     *
     * @param cacheManager 唯一初始化用
     */
    @Override
    public void setCacheManager(CacheManager cacheManager) {
        if (null == getCacheManager()) {
            super.setCacheManager(cacheManager);
        }
    }

    @Override
    protected void doUpdate(final Session session) {
        LOGGER.trace("尝试从Redis中更新/保存Session: {}", session.getId());
        final byte[] k = computeKey(session.getId());
        final byte[] f = SerialUtils.serialize(session.getId());
        final byte[] v = SerialUtils.serialize(session);
        final int expiration = (int) (session.getTimeout() / 1000);
        try {
            jedisTemplate.execute(new JedisTemplate.JedisActionNoResult() {

                @Override
                public void action(Jedis jedis) {
                    Pipeline p = jedis.pipelined();
                    p.hset(k, f, v);
                    if (expiration > 0) {
                        p.expire(k, expiration);
                    }
                    p.sync();
                }
            });
        } catch (JedisException e) {
            LOGGER.error("Redis连接异常！Session未写入", e);
        }
    }

    @Override
    protected void doDelete(Session session) {
        LOGGER.trace("尝试从Redis中删除Session: {}", session.getId());
        final byte[] k = computeKey(session.getId());
        try {
            jedisTemplate.execute(new JedisTemplate.JedisActionNoResult() {

                @Override
                public void action(Jedis jedis) {
                    jedis.del(k);
                }
            });
        } catch (JedisException e) {
            LOGGER.error("Redis连接异常！未删除", e);
        }
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        proxySessionAttributes(session);
        LOGGER.trace("分配新Session的ID: {}", sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(final Serializable sessionId) {
        LOGGER.trace("尝试从Redis中读取Session: {}", sessionId);
        Session session = null;
        try {
            session = jedisTemplate.execute(new JedisTemplate.JedisAction<Session>() {

                @Override
                public Session action(Jedis jedis) {
                    byte[] bs = jedis.hget(computeKey(sessionId), SerialUtils.serialize(sessionId));
                    Object value = SerialUtils.deserialize(bs);
                    return null == value ? null : (Session) value;
                }
            });
            proxySessionAttributes(session);
        } catch (JedisException e) {
            // Redis如果挂了……
            LOGGER.error("Redis连接异常！伪造Session", e);
            return null;
        }
        LOGGER.trace("读取结束，是否取到？ {}", null != session);
        // 缓存Session
        cache(session, sessionId);
        return session;
    }

    /**
     * 对原始Session 的属性进行代理，以支持按单个属性的粒度进行控制<br/>
     *
     * @param session 原始的Session
     */
    private void proxySessionAttributes(Session session) {
        if (session instanceof SimpleSession) {
            SimpleSession simpleSession = ((SimpleSession) session);
            Map map = simpleSession.getAttributes();
            if (!(map instanceof ProxyMap)) {
                map = new ProxyMap(new HashMap());
                simpleSession.setAttributes(map);
            }
            RedisMap redisMap = new RedisMap(this.jedisTemplate, computeKey(session.getId()),
                    SerialUtils.serialize(session.getId()));
            ((ProxyMap) map).setRedisMap(redisMap);
        }
    }

    /**
     * 清除并同步本地缓存（更新）
     */
    public void clearAndSyncCache() {
        Cache<Serializable, Session> activeSessionsCache = getActiveSessionsCache();
        if (activeSessionsCache != null) {
            Collection<Session> values = activeSessionsCache.values();
            if (CollectionUtils.isNotEmpty(values)) {
                for (Session session : values) {
                    doUpdate(session);
                }
            }
            activeSessionsCache.clear();
        }
    }

    /**
     * 根据key和 {@link #prefix} 计算Redis key
     *
     * @param key key
     * @return 计算后结果
     */
    private byte[] computeKey(Serializable key) {
        if (key instanceof byte[]) {
            return (byte[]) key;
        }

        byte[] k;
        if (key instanceof String) {
            k = ((String) key).getBytes();
        } else {
            k = SerializationUtils.serialize(key);
        }

        if (prefix == null || prefix.length == 0)
            return k;

        byte[] result = Arrays.copyOf(prefix, prefix.length + k.length);
        System.arraycopy(k, 0, result, prefix.length, k.length);
        return result;
    }
}
