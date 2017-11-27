package cn.xyz.chaos.common.utils.digest;

import cn.xyz.chaos.common.utils.digest.Digester;
import cn.xyz.chaos.common.utils.digest.impl.Md5Digester;
import cn.xyz.chaos.common.utils.digest.impl.Sha256Digester;

/**
 * 每次获取的摘要均不一样，比较需要使用matches比较
 */
public class Digests2Utils {
    private static final Md5Digester md5Digester = new Md5Digester();
    private static final Sha256Digester sha256Digester = new Sha256Digester();

    public static byte[] digest(byte[] message, Digester.Algorithm algorithm) {
        if (algorithm == Digester.Algorithm.MD5) {
            return md5Digester.digest(message);
        } else {
            return sha256Digester.digest(message);
        }
    }

    public static String digest(String message, Digester.Algorithm algorithm) {
        if (algorithm == Digester.Algorithm.MD5) {
            return md5Digester.digest(message);
        } else {
            return sha256Digester.digest(message);
        }
    }

    public static boolean matches(byte[] message, byte[] digest, Digester.Algorithm algorithm) {
        if (algorithm == Digester.Algorithm.MD5) {
            return md5Digester.matches(message, digest);
        } else {
            return sha256Digester.matches(message, digest);
        }
    }

    public static boolean matches(String message, String digest, Digester.Algorithm algorithm) {
        if (algorithm == Digester.Algorithm.MD5) {
            return md5Digester.matches(message, digest);
        } else {
            return sha256Digester.matches(message, digest);
        }
    }
}
