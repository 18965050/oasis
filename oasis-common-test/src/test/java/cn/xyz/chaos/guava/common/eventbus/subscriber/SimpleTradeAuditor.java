package cn.xyz.chaos.guava.common.eventbus.subscriber;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import cn.xyz.chaos.guava.common.eventbus.event.TradeAccountEvent;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 *
 * @author lvchenggang
 *
 */
public class SimpleTradeAuditor {

    private List<TradeAccountEvent> tradeEvents = Lists.newArrayList();

    public SimpleTradeAuditor(EventBus eventBus) {
        checkNotNull(eventBus, "EventBus can't be null");
        eventBus.register(this);
    }

    @Subscribe
    public void auditTrade(TradeAccountEvent tradeAccountEvent) {
        tradeEvents.add(tradeAccountEvent);
        System.out.println("Received trade " + tradeAccountEvent);
    }

    public List<TradeAccountEvent> getTradeEvents() {
        return tradeEvents;
    }
}
