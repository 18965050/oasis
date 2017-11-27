package cn.xyz.chaos.guava.common.cache;

import com.google.common.cache.RemovalCause;
import com.google.common.cache.RemovalListener;

/**
 *
 * @author lvchenggang
 *
 * @param <K>
 * @param <V>
 */
public abstract class BaseRemovalListener<K, V> implements RemovalListener<K, V> {

    protected RemovalCause removalCause;
    protected K removedKey;
    protected V removedValue;

    public RemovalCause getRemovalCause() {
        return removalCause;
    }

    public K getRemovedKey() {
        return removedKey;
    }

    public V getRemovedValue() {
        return removedValue;
    }
}
