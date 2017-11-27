package cn.xyz.chaos.mq.fmq.wrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.focustech.fmq.producer.Future;
import com.focustech.fmq.producer.IProducer;
import com.focustech.fmq.producer.ISendCallBack;
import com.focustech.fmq.producer.ProducerMessage;
import com.focustech.fmq.producer.SendResult;
import com.focustech.fmq.producer.exception.FmqException;
import com.focustech.fmq.producer.exception.FmqException.MessageStatus;
import com.focustech.fmq.producer.impl.FmqProducer;

/**
 * fmq 用起来有点别扭。加个抽象
 */
public class FmqProducerWrapper {

    private static final Logger logger = LoggerFactory.getLogger(FmqProducerWrapper.class);

    private IProducer producer;

    public FmqProducerWrapper(Map<String, String> configMap) {
        this.producer = new FmqProducer(configMap);
    }

    public FmqProducerWrapper(IProducer producer) {
        this.producer = producer;
    }

    public MessageStatus findMessageStatusById(String msgId) {
        return this.producer.findMessageStatusById(msgId);
    }

    /**
     * 同步发送消息，无特殊的性能需求等情况推荐使用，以确认消息发送成功。
     * @param message 消息
     * @return 发送结果，无特殊需求，一般可不处理
     * @throws FmqException 发送异常，如消息格式不对
     */
    public SendResult sendSync(FmqMessage message) throws FmqException {
        return this.producer.sendSync(message.getTopic(), convertMessage(message));
    }

    /**
     * 异步发送消息，性能比同步发送高N倍，但不保成功证发送时间，调用成功不代表已经发送成功。无负载无消息堆积情况下，延时一般在秒内。
     * @param message 消息
     * @param callBack 发送完成回调
     * @return Future，可用来同步
     * @throws FmqException 消息发送异常，如 message topic 为 null
     */
    public Future sendAsync(FmqMessage message, ISendCallBack callBack) throws FmqException {
        return this.producer.sendAsync(message.getTopic(), convertMessage(message), callBack);
    }

    /**
     * 异步批量发送消息。性能比同步发送高N倍，但不保成功证发送时间调用成功不代表已经发送成功。无负载无消息堆积情况下，延时一般在秒内。
     * @param messageList 消息列表
     * @param callBack 发送完成回调
     * @return Future，可用来同步
     * @throws FmqException 消息发送异常，如 messageList 为 empty
     */
    public Future batchSendAsync(List<FmqMessage> messageList, ISendCallBack callBack) throws FmqException {
        if (null == messageList || messageList.isEmpty()) {
            return this.producer.sendAsync(null, null, callBack);
        }
        String topic = null;
        List<ProducerMessage> pMsgList = new ArrayList<ProducerMessage>();
        for (FmqMessage message : messageList) {
            pMsgList.add(convertMessage(message));
            if (null == topic) {
                topic = message.getTopic();
            }
        }
        return this.producer.batchSendAsync(topic, pMsgList, callBack);
    }

    /**
     * 关闭。释放资源，保存数据。
     */
    public void close() {
        this.producer.close();
    }

    /**
     * 转换封装的 FmqMessage 为 Fmq 内部使用的 ProducerMessage
     * @param message
     * @return
     */
    private ProducerMessage convertMessage(FmqMessage message) {
        String topic = message.getTopic();
        byte[] value = message.getValue();
        byte[] key = message.getKey();
        boolean retry = message.isToBeRetried();
        return new ProducerMessage(this.producer.generateMessageId(topic), key, value, retry);
    }

}
