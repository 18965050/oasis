package cn.xyz.chaos.guava.common.eventbus.subscriber;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 *
 * @author lvchenggang
 *
 */
public class DeadEventSubscriber {

    private List<DeadEvent> deadEvents = Lists.newArrayList();

    public DeadEventSubscriber(EventBus eventBus) {
        eventBus.register(this);
    }

    @Subscribe
    public void handleUnsubscribedEvent(DeadEvent deadEvent) {
        System.out.println("No subscribers for " + deadEvent.getEvent());
        deadEvents.add(deadEvent);
    }

    public List<DeadEvent> getDeadEvents() {
        return deadEvents;
    }
}
