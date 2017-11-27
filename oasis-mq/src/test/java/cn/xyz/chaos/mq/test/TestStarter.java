package cn.xyz.chaos.mq.test;

import cn.xyz.chaos.mq.fmq.wrapper.FmqMessage;
import cn.xyz.chaos.mq.fmq.wrapper.FmqProducerWrapper;
import com.focustech.fmq.producer.SendResult;
import com.focustech.fmq.producer.exception.FmqException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mq/fmq.xml")
public class TestStarter {

    @Autowired
    private FmqProducerWrapper producer;

    @Test
    public void send() throws InterruptedException {
        try {
            SendResult sendResult = producer
                    .sendSync(new FmqMessage("xyz_test_topic", MessageFactory.getNewInstance()));
            Assert.assertTrue(sendResult.isSuccess());
        } catch (FmqException e) {
            Assert.fail("不应该发送失败");
        }
        Thread.sleep(1000); // 等待 1s 应该收到消息
        int i = MessageCount.getCount().get();
        Assert.assertEquals(i, 1);
    }

}
