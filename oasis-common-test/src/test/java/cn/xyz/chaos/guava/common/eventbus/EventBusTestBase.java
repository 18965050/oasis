package cn.xyz.chaos.guava.common.eventbus;

import cn.xyz.chaos.guava.common.eventbus.event.BuyEvent;
import cn.xyz.chaos.guava.common.eventbus.event.SellEvent;
import cn.xyz.chaos.guava.common.eventbus.event.TradeAccountEvent;

import com.google.common.eventbus.EventBus;

/**
 *
 * @author lvchenggang
 *
 */
public abstract class EventBusTestBase {

    protected TradeAccountEvent.Builder getTradeAccountEventBuilder() {
        return new TradeAccountEvent.Builder();
    }

    protected BuyEvent.Builder buyEventBuilder() {
        return new BuyEvent.Builder();
    }

    protected SellEvent.Builder sellEventBuilder() {
        return new SellEvent.Builder();
    }

    protected EventBus getEventBus() {
        return new EventBus();
    }

}
