package cn.xyz.chaos.common.utils.encryption;

/**
 * 目前系统中均使用PBEWithMD5AndDES方式加密
 */
public interface Encryptor {

    String DEFAULT_PASSWORD = "INSXYZ";

    enum Algorithm {
        PBEWithMD5AndDES,
        PBEWithMD5AndTripleDES
    }

    byte[] encrypt(byte[] input) throws EncryptorException;

    byte[] decrypt(byte[] input) throws EncryptorException;

    String encrypt(String input) throws EncryptorException;

    String decrypt(String input) throws EncryptorException;

}
