package cn.xyz.chaos.common.utils.encryption.impl;

import cn.xyz.chaos.common.utils.encryption.Encryptor;
import cn.xyz.chaos.common.utils.encryption.EncryptorException;
import org.apache.commons.codec.binary.Hex;
import org.jasypt.encryption.pbe.StandardPBEByteEncryptor;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lcg on 2015/7/17.
 */
public class DesEncryptor implements Encryptor {
    private Logger logger = LoggerFactory.getLogger(DesEncryptor.class);
    private StandardPBEByteEncryptor byteEncryptor;
    private StandardPBEStringEncryptor stringEncryptor;

    public DesEncryptor() {
        byteEncryptor = new StandardPBEByteEncryptor();
        byteEncryptor.setAlgorithm(Algorithm.PBEWithMD5AndDES.name());
        byteEncryptor.setPassword(DEFAULT_PASSWORD);
        byteEncryptor.initialize();

        stringEncryptor = new StandardPBEStringEncryptor();
        stringEncryptor.setAlgorithm(Algorithm.PBEWithMD5AndDES.name());
        stringEncryptor.setPassword(DEFAULT_PASSWORD);
        stringEncryptor.initialize();
    }

    @Override
    public byte[] encrypt(byte[] input) {
        try {
            return byteEncryptor.encrypt(input);
        } catch (Exception ex) {
            logger.error(String.format("加密[%s]异常", input), ex);
            throw new EncryptorException("加密异常", ex);
        }
    }

    @Override
    public String encrypt(String input) {
        try {
            String ciphertext = stringEncryptor.encrypt(input);
            return Hex.encodeHexString(ciphertext.getBytes());
        } catch (Exception ex) {
            logger.error(String.format("加密[%s]异常", input), ex);
            throw new EncryptorException("加密异常", ex);
        }
    }

    public byte[] decrypt(byte[] input) {
        try {
            return byteEncryptor.decrypt(input);
        } catch (Exception ex) {
            logger.error(String.format("解密[%s]异常", input), ex);
            throw new EncryptorException("解密异常", ex);
        }
    }

    public String decrypt(String input) {
        try {
            String plaintext = new String(Hex.decodeHex(input.toCharArray()));
            return stringEncryptor.decrypt(plaintext);
        } catch (Exception ex) {
            logger.error(String.format("解密[%s]异常", input), ex);
            throw new EncryptorException("解密异常", ex);
        }
    }
}
