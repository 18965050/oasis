package cn.xyz.chaos.mq.fmq;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import com.focustech.fmq.consumer.IConsumer;
import com.focustech.fmq.consumer.IMessageHandler;
import com.focustech.fmq.consumer.impl.FmqConsumer;
import com.focustech.fmq.producer.exception.FmqException;

import cn.xyz.chaos.mq.SuffixClientId;

/**
 *
 */
public class FmqConsumerManager implements DisposableBean, InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(FmqProducerFactoryBean.class);

    private static List<IConsumer> consumers = new LinkedList<IConsumer>();

    private List<FmqConsumerConfig> consumerConfigs;

    private Properties extraCommonProps;

    private String brokerListString;

    private String zkListString;

    private String clientId;

    private String topicSuffix;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (extraCommonProps == null) {
            extraCommonProps = new Properties();
        }
        putAndAssertNotBlank("brokerListString", brokerListString, extraCommonProps, "metadata.broker.list");
        putAndAssertNotBlank("zkListString", zkListString, extraCommonProps, "zookeeper.connect");
        putAndAssertNotBlank("clientId", clientId, extraCommonProps, "client.id");
        putAndAssertNotBlank("topicSuffix", topicSuffix, extraCommonProps, "topic.suffix");
        Assert.notEmpty(consumerConfigs, "Consumer 配置至少需要有一项");
        startConsumers(consumerConfigs, extraCommonProps);
    }

    private void startConsumers(List<FmqConsumerConfig> consumerConfigs, Properties commonProps) throws FmqException {
        for (FmqConsumerConfig config : consumerConfigs) {
            String topic = config.getTopic();
            IMessageHandler messageHandler = config.getMessageHandler();
            String groupId = config.getGroupId();
            Properties finalProps = new Properties(); // 每个 consumer 的 properties
            finalProps.putAll(commonProps);
            Properties extraProps = config.getExtraProps();
            if (!CollectionUtils.isEmpty(extraProps)) {
                finalProps.putAll(extraProps);
            }
            String idStr = SuffixClientId.suffix(finalProps.getProperty("client.id") + "_" + groupId);
            finalProps.setProperty("group.id", groupId);
            finalProps.setProperty("client.id", idStr + "_client");
            // 默认随机生成, consumer.id不同
            // finalProps.setProperty("consumer.id", idStr + "_consumer");
            if (logger.isTraceEnabled()) {
                logger.trace("准备初始化 Consumer，topic:[{}], groupId:[{}] props 为: \n {}", topic, groupId,
                        finalProps.toString());
            }
            IConsumer consumer = new FmqConsumer(finalProps);
            consumer.receive(topic, messageHandler);
            consumers.add(consumer);
        }
    }

    private void putAndAssertNotBlank(String key, String value, Properties props, String propKey) {
        if (StringUtils.isNotBlank(value)) {
            props.setProperty(propKey, value);
        }
        Assert.state(StringUtils.isNotBlank(props.getProperty(propKey)),
                key + " 不可为 Blank！请设置 Manager 对应的值（优先），" + " 或 extraCommonProps 中 key `\"" + propKey + "\"对应的值。");
    }

    @Override
    public void destroy() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("FmqConsumerManager destroy, consumers will be closed。\n consumers size: {}",
                    consumers.size());
        }
        for (IConsumer consumer : consumers) {
            consumer.close();
        }
    }

    public void setConsumerConfigs(List<FmqConsumerConfig> consumerConfigs) {
        this.consumerConfigs = consumerConfigs;
    }

    public void setExtraCommonProps(Properties extraCommonProps) {
        this.extraCommonProps = extraCommonProps;
    }

    public void setBrokerListString(String brokerListString) {
        this.brokerListString = brokerListString;
    }

    public void setZkListString(String zkListString) {
        this.zkListString = zkListString;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setTopicSuffix(String topicSuffix) {
        this.topicSuffix = topicSuffix;
    }
}
