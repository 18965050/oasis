package cn.xyz.chaos.mq.test;

import cn.xyz.chaos.mq.fmq.wrapper.AbstractMessageHandler;
import org.junit.Assert;

/**
 * 消息处理类通常简单继承 {@link cn.xyz.chaos.mq.fmq.wrapper.AbstractMessageHandler}，实现其抽象方法即可。<br/>
 * 如果有特殊序列化需求，可以实现 {@link com.focustech.fmq.consumer.IMessageHandler} 自行处理 byte[] 的反序列化
 */
public class TestMessageHandler extends AbstractMessageHandler<TestMessageEntity> {
    @Override
    public void doHandleMessage(TestMessageEntity o) {
        // .... 在单独线程中处理，不要抛出异常，业务异常请自行处理
        // 抛到外层的异常，只会统一打印日志，消息会继续消费
        // 相当于消息未成功消费，丢失掉了

        // 同一个 handler 同一个 topic 和 groupId 的消息处理可以认为是单线程。
        // 如果一条消息不处理完成，不会处理下一条，会堆积。
        System.out.println("接收到消息，处理！");
        Assert.assertNotNull(o);
        TestMessageEntity target = MessageFactory.getNewInstance();
        Assert.assertEquals(target.getOneField(), o.getOneField());
        Assert.assertEquals(target.getTwoField(), o.getTwoField());
        Assert.assertEquals(target, o);
        int i = MessageCount.getCount().incrementAndGet();
        Assert.assertEquals(i, 1);
    }
}
