package cn.xyz.chaos.examples.soa.functional;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.xyz.chaos.examples.soa.pi.Test2Provider;

/**
 * cn.xyz.soa.functional
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/3/12 17:20.
 */
// @RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/app-provider-and-consumer.xml" })
public class DubboTest {

    @Autowired
    private Test2Provider test2Provider;

    // @Test
    public void testTest2() throws Exception {
        String s = test2Provider.returnTest1And2String();
        assertEquals("Test1test2", s);
    }
}
