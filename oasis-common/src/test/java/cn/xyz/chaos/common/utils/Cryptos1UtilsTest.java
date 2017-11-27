package cn.xyz.chaos.common.utils;

import org.junit.Assert;
import org.junit.Test;

import cn.xyz.chaos.common.utils.encryption.Encryptor;
import cn.xyz.chaos.common.utils.encryption.Encryptor2Utils;

/**
 * Created by lcg on 2015/7/16.
 */
public class Cryptos1UtilsTest {
    @Test
    public void encrypt() {
        String input = "ins1234";
        for (int i = 0; i < 100; i++) {
            String cryptotext = Encryptor2Utils.encrypt(input, Encryptor.Algorithm.PBEWithMD5AndDES);
            String plaintext = Encryptor2Utils.decrypt(cryptotext, Encryptor.Algorithm.PBEWithMD5AndDES);
            System.out.println(String.format("[%s] - [%s] - [%s]", input, cryptotext, plaintext));
            Assert.assertTrue(input.equals(plaintext));
        }
    }

    @Test
    public void encryptTriple() {
        String input = "ins1234";
        for (int i = 0; i < 100; i++) {
            String cryptotext = Encryptor2Utils.encrypt(input, Encryptor.Algorithm.PBEWithMD5AndTripleDES);
            String plaintext = Encryptor2Utils.decrypt(cryptotext, Encryptor.Algorithm.PBEWithMD5AndTripleDES);
            System.out.println(String.format("[%s] - [%s] - [%s]", input, cryptotext, plaintext));
            Assert.assertTrue(input.equals(plaintext));
        }
    }
}
