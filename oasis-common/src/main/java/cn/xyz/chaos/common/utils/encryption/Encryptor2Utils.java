package cn.xyz.chaos.common.utils.encryption;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.common.utils.encryption.impl.DesEncryptor;
import cn.xyz.chaos.common.utils.encryption.impl.TripleDesEncryptor;

/**
 * 目前系统中均使用PBEWithMD5AndDES方式加密，PBEWithMD5AndTripleDES后期删除
 */
public class Encryptor2Utils {
    private static Logger logger = LoggerFactory.getLogger(TripleDesEncryptor.class);
    private static DesEncryptor desEncryptor = new DesEncryptor();
    private static TripleDesEncryptor tripleDesEncryptor = new TripleDesEncryptor();

    public static byte[] encrypt(byte[] input, Encryptor.Algorithm algorithm) {
        if (algorithm == Encryptor.Algorithm.PBEWithMD5AndTripleDES) {
            return tripleDesEncryptor.encrypt(input);
        } else {
            return desEncryptor.encrypt(input);
        }
    }

    public static String encrypt(String input, Encryptor.Algorithm algorithm) {
        String cryptotext = null;
        try {
            if (algorithm == Encryptor.Algorithm.PBEWithMD5AndTripleDES) {
                cryptotext = tripleDesEncryptor.encrypt(input);
            } else {
                cryptotext = desEncryptor.encrypt(input);
            }
            return Hex.encodeHexString(cryptotext.getBytes());
        } catch (Exception ex) {
            logger.error(String.format("加密[%s]异常", input), ex);
            throw new EncryptorException("加密异常", ex);
        }
    }

    public static byte[] decrypt(byte[] input, Encryptor.Algorithm algorithm) {
        if (algorithm == Encryptor.Algorithm.PBEWithMD5AndTripleDES) {
            return tripleDesEncryptor.decrypt(input);
        } else {
            return desEncryptor.decrypt(input);
        }
    }

    public static String decrypt(String input, Encryptor.Algorithm algorithm) {
        try {
            String plaintext = new String(Hex.decodeHex(input.toCharArray()));
            if (algorithm == Encryptor.Algorithm.PBEWithMD5AndTripleDES) {
                return tripleDesEncryptor.decrypt(plaintext);
            } else {
                return desEncryptor.decrypt(plaintext);
            }
        } catch (Exception ex) {
            logger.error(String.format("解密[%s]异常", input), ex);
            throw new EncryptorException("解密异常", ex);
        }
    }
}
