package cn.xyz.chaos.mq.fmq.wrapper;

import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.SerializationUtils;

/**
 * fmq Message 的包装
 */
public class FmqMessage {

    private final String topic;
    private final byte[] value;
    private final byte[] key;
    private final boolean toBeRetried;

    private final static Random randomKeyGe = new Random();

    /**
     * @param topic 必须指定 topic，根据 topic 消费
     * @param value 消息内容，byte[]，自己控制序列化方式。注意，自己控制序列化方式需要在 Consumer 中使用同样特定的反序列化方式，不可用默认。
     * @param key 业务 key，需要保证在同一个分区的时候传相同的值，会路由到同一个分区. null 的时候会随机分配
     * @param toBeRetried 是否是要被重试的消息。true，表示这条是之前发送过的，又重试的消息。false，为正常发送的消息, 默认 false
     */
    public FmqMessage(String topic, byte[] value, byte[] key, boolean toBeRetried) {
        Assert.state(StringUtils.isNotBlank(topic), "topic 不可以为 Blank，请使用正确预置的 topic。");
        Assert.notNull(value, "消息内容不可为 null");
        this.topic = topic;
        this.key = key != null ? key : getRandomBytes();
        this.value = value;
        this.toBeRetried = toBeRetried;
    }

    /**
     * @see #FmqMessage(String, byte[], byte[], boolean)
     */
    public FmqMessage(String topic, byte[] value) {
        this(topic, value, null, false);
    }

    /**
     * 使用默认序列化方式构造指定 topic 的消息。<br/>
     * 在配置多分区，并行被客户端消费的情况下，不保证顺序。单分区保证顺序。
     * @see #FmqMessage(String, Object, Object)
     */
    public FmqMessage(String topic, Object value) {
        // this(topic, ConvertUtil.toByteArray(value));
        this(topic, SerializationUtils.serialize(value));
    }

    /**
     * @param topic 必须指定 topic，根据 topic 消费
     * @param value 消息内容,内部使用默认方式序列化
     * @param key 业务 key，需要保证在同一个分区的时候传相同的值，会 路由到同一个分区，内部使用默认方式序列化
     * @see #FmqMessage(String, byte[], byte[], boolean)
     */
    public FmqMessage(String topic, Object value, Object key) {
        this(topic, SerializationUtils.serialize(value), key == null ? null : SerializationUtils.serialize(key), false);
    }

    private byte[] getRandomBytes() {
        int value = randomKeyGe.nextInt();
        return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
    }

    public String getTopic() {
        return topic;
    }

    public byte[] getValue() {
        return value;
    }

    public byte[] getKey() {
        return key;
    }

    public boolean isToBeRetried() {
        return toBeRetried;
    }
}
