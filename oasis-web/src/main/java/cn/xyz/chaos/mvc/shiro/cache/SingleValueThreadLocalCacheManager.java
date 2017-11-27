package cn.xyz.chaos.mvc.shiro.cache;

import java.io.Serializable;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.session.Session;

/**
 * cn.xyz.chaos.mvc.shiro.cache 单值ThreadLocal的Manager
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/11/3 18:03.
 */
public class SingleValueThreadLocalCacheManager extends AbstractCacheManager {
    @Override
    protected Cache createCache(String name) throws CacheException {
        return new SingleValueThreadLocalCache<Serializable, Session>(name);
    }
}
