package cn.xyz.chaos.mq.test;

import sun.security.jca.GetInstance;

/**
 *
 */
public class MessageFactory {

    static TestMessageEntity getNewInstance() {
        TestMessageEntity entity = new TestMessageEntity();
        entity.setOneField("this is the first one");
        entity.setTwoField(2);
        return entity;
    }

}
