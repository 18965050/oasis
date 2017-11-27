package cn.xyz.chaos.mvc.abuse.store;

import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SerializationUtils;
import org.springside.modules.nosql.redis.JedisTemplate;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.abuse.store
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 15:40.
 */
public class RedisStore implements Store {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisStore.class);
    public static final String SEPARATOR = ":";

    private String name;
    private JedisTemplate jedisTemplate;
    private byte[] prefix;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Store 的name 不可为空");
        prefix = name.concat(SEPARATOR).getBytes(Charset.forName("UTF-8"));
        this.name = name;
    }

    @Override
    public void save(final String key, final Object value, final int expiration) {
        LOGGER.trace("Putting object in redis store [" + name + "] for key [" + key + "]");
        try {
            if (StringUtils.isBlank(key)) {
                return;
            } else {
                jedisTemplate.execute(new JedisTemplate.JedisActionNoResult() {

                    @Override
                    public void action(Jedis jedis) {
                        Pipeline p = jedis.pipelined();
                        byte[] k = computeKey(key);
                        p.set(k, SerializationUtils.serialize(value));
                        if (expiration > 0) {
                            p.expire(k, expiration);
                        }
                        p.sync();
                    }
                });
            }
        } catch (Throwable t) {
            LOGGER.error("保存 {} 对应的值到Redis Store出错", key);
            return;
        }
    }

    @Override
    public <T> T get(final String key) {
        LOGGER.trace("Getting object from redis store [" + name + "] for key [" + key + "]");
        try {
            if (StringUtils.isBlank(key)) {
                return null;
            } else {
                return jedisTemplate.execute(new JedisTemplate.JedisAction<T>() {

                    @Override
                    public T action(Jedis jedis) {
                        byte[] element = jedis.get(computeKey(key));
                        if (element == null) {
                            LOGGER.trace("Element for [" + key + "] is null.");
                            return null;
                        } else {
                            // noinspection unchecked
                            return (T) SerializationUtils.deserialize(element);
                        }
                    }
                });
            }
        } catch (Throwable t) {
            LOGGER.error("从Redis获取 {} 对应的值出错，返回Null", key);
            return null;
        }
    }

    @Override
    public boolean exist(final String key) {
        LOGGER.trace("Is exist object in redis store [" + name + "] for key [" + key + "] or not");
        try {
            if (StringUtils.isBlank(key)) {
                return false;
            } else {
                return jedisTemplate.execute(new JedisTemplate.JedisAction<Boolean>() {

                    @Override
                    public Boolean action(Jedis jedis) {
                        return jedis.exists(computeKey(key));
                    }
                });
            }
        } catch (Throwable t) {
            LOGGER.error("从Redis校验 {} 对应的值是否存在，返回false", key);
            return false;
        }
    }

    /**
     * 根据key和 {@link #name} 计算Redis key
     *
     * @param key key
     * @return 计算后结果
     */
    private byte[] computeKey(String key) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name), "Store 的name 不可为空");

        byte[] k = ((String) key).getBytes();
        byte[] result = Arrays.copyOf(prefix, prefix.length + k.length);
        System.arraycopy(k, 0, result, prefix.length, k.length);
        return result;
    }

    public void setJedisTemplate(JedisTemplate jedisTemplate) {
        this.jedisTemplate = jedisTemplate;
    }
}
