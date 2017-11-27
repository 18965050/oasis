package cn.xyz.chaos.examples.soa.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.xyz.chaos.examples.soa.pi.Test1Provider;
import cn.xyz.chaos.examples.soa.pi.Test2Provider;

/**
 * cn.xyz.soa.pc
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/3/12 16:44.
 */
@Component
public class Test2ProviderImpl implements Test2Provider {

    @Autowired
    private Test1Provider test1Provider;

    @Override
    public String returnTest1And2String() {
        return test1Provider.returnTest1String() + "test2";
    }

}
