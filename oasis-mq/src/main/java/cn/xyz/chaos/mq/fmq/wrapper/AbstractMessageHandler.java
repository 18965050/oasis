package cn.xyz.chaos.mq.fmq.wrapper;

import org.springframework.util.SerializationUtils;

import com.focustech.fmq.consumer.IMessageHandler;

/**
 * 抽象实现，使用默认序列化方法。<br/>
 * 自定义序列化请不要继承该类，直接实现 com.focustech.fmq.consumer.IMessageHandler
 * @param <T>
 */
public abstract class AbstractMessageHandler<T> implements IMessageHandler {
    @Override
    @SuppressWarnings("unchecked")
    public void handleMessage(byte[] bytes) {
        T o = (T) SerializationUtils.deserialize(bytes);
        doHandleMessage(o);
    }

    public abstract void doHandleMessage(T o);

}
