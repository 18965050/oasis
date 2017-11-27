package cn.xyz.chaos.guava.common.eventbus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.guava.common.eventbus.event.SellEvent;
import cn.xyz.chaos.guava.common.eventbus.subscriber.DeadEventSubscriber;
import cn.xyz.chaos.guava.common.eventbus.subscriber.SimpleTradeAuditor;

import com.google.common.eventbus.EventBus;

/**
 * {@link com.google.common.eventbus.DeadEvent}单元测试
 * <p />
 * 如果没有任何Subscriber订阅某个事件消息,则此消息将被Wrapper为DeadEvent, <br />
 * 我们可以用一个统一的Subscriber来处理DeadEvent
 *
 * @author lvchenggang
 *
 */
public class DeadEventSubscriberTest extends EventBusTestBase {

    private DeadEventSubscriber deadEventSubscriber;
    private SimpleTradeAuditor tradeAuditor;
    private EventBus eventBus;

    @Before
    public void setUp() {
        eventBus = getEventBus();
        // Purposely mis-configured - different EventBus, common error
        tradeAuditor = new SimpleTradeAuditor(getEventBus());
        deadEventSubscriber = new DeadEventSubscriber(eventBus);
    }

    @Test
    public void testPublishNoSubscribers() {
        SellEvent sellEvent = sellEventBuilder().build();
        eventBus.post(sellEvent);
        assertThat(tradeAuditor.getTradeEvents().isEmpty(), is(true));
        assertThat(deadEventSubscriber.getDeadEvents().size(), is(1));
        SellEvent wrappedSellEvent = (SellEvent) deadEventSubscriber.getDeadEvents().get(0).getEvent();
        assertThat(wrappedSellEvent, is(sellEvent));
    }

}
