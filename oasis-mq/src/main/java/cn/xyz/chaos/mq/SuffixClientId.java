package cn.xyz.chaos.mq;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 给 clientId 加上各机器不同的后缀
 */
public class SuffixClientId {

    private static final String INET_ADDRESS;

    static {
        String temp = null;
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            temp = localHost.toString().replace('/', '_');
        } catch (UnknownHostException e) {
            temp = RandomStringUtils.randomAlphanumeric(6);
        }
        INET_ADDRESS = temp;
    }

    public static String suffix(String clientId) {
        return clientId + "_" + INET_ADDRESS;
    }

}
