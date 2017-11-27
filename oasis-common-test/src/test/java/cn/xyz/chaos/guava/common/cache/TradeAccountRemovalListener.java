package cn.xyz.chaos.guava.common.cache;

import cn.xyz.chaos.guava.common.model.TradeAccount;

import com.google.common.cache.RemovalNotification;

/**
 *
 * @author lvchenggang
 *
 */
public class TradeAccountRemovalListener extends BaseRemovalListener<String, TradeAccount> {

    public void onRemoval(RemovalNotification<String, TradeAccount> notification) {
        this.removalCause = notification.getCause();
        this.removedKey = notification.getKey();
        this.removedValue = notification.getValue();
    }
}
