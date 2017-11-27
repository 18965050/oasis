package cn.xyz.chaos.dubbo.registry.redis2;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.registry.Registry;
import com.alibaba.dubbo.registry.redis.RedisRegistry;
import com.alibaba.dubbo.registry.support.AbstractRegistryFactory;

/**
 * 继承 AbstractRegistryFactory 修复重复创建 Registry 问题。
 */
public class RedisRegistryFactory extends AbstractRegistryFactory {

    @Override
    protected Registry createRegistry(URL url) {
        return new RedisRegistry(url);
    }
}
