package cn.xyz.chaos.examples.soa.functional;

import com.alibaba.dubbo.container.Main;

/**
 * cn.xyz.soa.functional
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/3/12 16:49.
 */
public class SoaExample {

    public static final String SPRING_XML_PATH = "classpath:/spring/app-provider.xml";

    // public static final String SPRING_XML_PATH = "classpath:/spring/app-provider-and-consumer.xml";

    public static void main(String[] args) {
        // start(SPRING_XML_PATH);
        Main.main(args);
    }

    static void start(String resource) {
        System.setProperty("dubbo.spring.config", resource);
        Main.main(new String[0]);
    }

}
