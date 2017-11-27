package cn.xyz.chaos.mq.fmq;

import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.focustech.fmq.consumer.IMessageHandler;

/**
 * Fmq Consumer 常用配置
 */
public class FmqConsumerConfig {

    private Properties extraProps;

    private IMessageHandler messageHandler;

    private String topic;

    private String groupId;

    public FmqConsumerConfig(String topic, String groupId, IMessageHandler messageHandler) {
        Assert.state(StringUtils.isNotBlank(topic), "topic 不可以为 Blank，请使用正确预置的 topic。");
        Assert.state(StringUtils.isNotBlank(groupId), "groupId 不可以为 Blank。");
        Assert.notNull(messageHandler, "messageHandler 不可为 null");
        this.topic = topic;
        this.groupId = groupId;
        this.messageHandler = messageHandler;
    }

    public void setExtraProps(Properties extraProps) {
        this.extraProps = extraProps;
    }

    public Properties getExtraProps() {
        return extraProps;
    }

    public IMessageHandler getMessageHandler() {
        return messageHandler;
    }

    public String getTopic() {
        return topic;
    }

    public String getGroupId() {
        return groupId;
    }

}
