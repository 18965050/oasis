package cn.xyz.chaos.examples.soa.p;

import org.springframework.stereotype.Component;

import cn.xyz.chaos.examples.soa.pi.Test1Provider;

/**
 * cn.xyz.soa.p
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/3/12 16:41.
 */
@Component
public class Test1ProviderImpl implements Test1Provider {
    @Override
    public String returnTest1String() {
        return "Test1";
    }
}
