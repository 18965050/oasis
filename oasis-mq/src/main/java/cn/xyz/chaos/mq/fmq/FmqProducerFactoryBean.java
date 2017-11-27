package cn.xyz.chaos.mq.fmq;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.util.Assert;

import cn.xyz.chaos.mq.SuffixClientId;
import cn.xyz.chaos.mq.fmq.wrapper.FmqProducerWrapper;

/**
 * fmq producer 配置。通常来讲，一个应用只需要一个单例的 producer 即可。
 */
public class FmqProducerFactoryBean extends AbstractFactoryBean<FmqProducerWrapper> {

    private static final Logger logger = LoggerFactory.getLogger(FmqProducerFactoryBean.class);

    private Map<String, String> configMap;

    public void setConfigMap(Map<String, String> configMap) {
        this.configMap = configMap;
    }

    @Override
    public Class<FmqProducerWrapper> getObjectType() {
        return FmqProducerWrapper.class;
    }

    @Override
    protected FmqProducerWrapper createInstance() throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("FmqProducerFactoryBean 创建 FmqProducer.");
        }
        String configErrorMsg = "configMap 不可为空，且必须配置 fmq broker 地址及 clientId";
        Assert.notEmpty(configMap, configErrorMsg);
        Assert.notNull(configMap.get("bootstrapServer"), configErrorMsg);
        String clientId = configMap.get("clientId");
        Assert.notNull(clientId, configErrorMsg);
        // 简单确保同一个应用，不同机器 address 不同
        configMap.put("clientId", SuffixClientId.suffix(clientId) + "_producer_client");
        return new FmqProducerWrapper(configMap);
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    protected void destroyInstance(FmqProducerWrapper instance) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("FmqProducerFactoryBean destroy, producer {} will be closed.", instance);
        }
        instance.close();
    }

}
