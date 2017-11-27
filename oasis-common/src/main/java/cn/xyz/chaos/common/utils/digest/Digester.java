package cn.xyz.chaos.common.utils.digest;

/**
 * 每次获取的摘要均不一样，比较需要使用matches比较
 */
public interface Digester {

    enum Algorithm {
        MD5("MD5"),
        SHA256("SHA-256");

        public String algorithm;

        Algorithm(String algorithm) {
            this.algorithm = algorithm;
        }
    }

    byte[] digest(byte[] message) throws DigesterException;

    String digest(String message) throws DigesterException;

    boolean matches(byte[] message, byte[] digest) throws DigesterException;

    boolean matches(String message, String digest) throws DigesterException;

}
