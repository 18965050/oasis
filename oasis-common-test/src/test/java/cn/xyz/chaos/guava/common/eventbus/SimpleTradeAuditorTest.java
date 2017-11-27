package cn.xyz.chaos.guava.common.eventbus;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.guava.common.eventbus.event.TradeAccountEvent;
import cn.xyz.chaos.guava.common.eventbus.subscriber.SimpleTradeAuditor;

import com.google.common.eventbus.EventBus;

/**
 * 简单消息发布订阅单元测试
 * <p />
 * <ui>使用EventBus,通常有如下三个步骤:
 * <li>获取EventBus对象引用,并进行注册</li>
 * <li>对事件触发访问添加@Subscribe注解</li>
 * <li>调用EventBus的post方法,进行事件发布</li> </ui>
 *
 * @author lvchenggang
 *
 */
public class SimpleTradeAuditorTest extends EventBusTestBase {

    private SimpleTradeAuditor auditor;
    private EventBus eventBus;
    private TradeAccountEvent.Builder builder;

    @Before
    public void setUp() {
        eventBus = getEventBus();
        auditor = new SimpleTradeAuditor(eventBus);
        builder = getTradeAccountEventBuilder();
    }

    /**
     * 发布订阅BuyEvent
     *
     * @throws Exception
     */
    @Test
    public void testSubscribeBuy() throws Exception {
        TradeAccountEvent tradeAccountEvent = builder.tradeType(TradeType.BUY).amount(5000.89).build();
        eventBus.post(tradeAccountEvent);
        List<TradeAccountEvent> events = auditor.getTradeEvents();
        assertThat(events.get(0).getTradeType(), is(TradeType.BUY));
        assertThat(events.get(0).getAmount(), is(5000.89));
    }

    /**
     * 发布订阅SellEvent
     *
     * @throws Exception
     */
    @Test
    public void testSubscribeSell() throws Exception {
        TradeAccountEvent tradeAccountEvent = builder.tradeType(TradeType.SELL).amount(5000.89).build();
        eventBus.post(tradeAccountEvent);
        List<TradeAccountEvent> events = auditor.getTradeEvents();
        assertThat(events.get(0).getTradeType(), is(TradeType.SELL));
        assertThat(events.get(0).getAmount(), is(5000.89));
    }

    /**
     * 发布订阅BuyEvent和SellEvent
     *
     * @throws Exception
     */
    @Test
    public void testSubscribeBuySell() throws Exception {
        TradeAccountEvent tradeAccountEvent = builder.tradeType(TradeType.SELL).amount(5000.89).build();
        eventBus.post(tradeAccountEvent);
        tradeAccountEvent = builder.tradeType(TradeType.BUY).amount(80000).build();
        eventBus.post(tradeAccountEvent);
        List<TradeAccountEvent> events = auditor.getTradeEvents();

        assertThat(events.get(0).getTradeType(), is(TradeType.SELL));
        assertThat(events.get(0).getAmount(), is(5000.89));

        assertThat(events.get(1).getTradeType(), is(TradeType.BUY));
        assertThat(events.get(1).getAmount(), is(80000D));
    }

}
