package cn.xyz.chaos.common.utils;

import cn.xyz.chaos.common.utils.digest.impl.Md5Digester;

/**
 * 每次获取的摘要均不一样，比较需要使用matches比较
 */
public class DigestsUtils {
    private static final Md5Digester md5Digester = new Md5Digester();

    public static byte[] digest(byte[] message) {
        return md5Digester.digest(message);
    }

    public static String digest(String message) {
        return md5Digester.digest(message);
    }

    public static boolean matches(byte[] message, byte[] digest) {
        return md5Digester.matches(message, digest);
    }

    public static boolean matches(String message, String digest) {
        return md5Digester.matches(message, digest);
    }
}
