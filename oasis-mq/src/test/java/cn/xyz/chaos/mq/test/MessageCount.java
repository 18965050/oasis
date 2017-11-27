package cn.xyz.chaos.mq.test;

import java.util.concurrent.atomic.AtomicInteger;

public class MessageCount {
    private static AtomicInteger count = new AtomicInteger(0);

    public static AtomicInteger getCount() {
        return count;
    }

}
