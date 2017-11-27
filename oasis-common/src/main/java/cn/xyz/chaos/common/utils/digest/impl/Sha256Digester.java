package cn.xyz.chaos.common.utils.digest.impl;

import cn.xyz.chaos.common.utils.digest.Digester;
import cn.xyz.chaos.common.utils.digest.DigesterException;
import org.jasypt.digest.StandardByteDigester;
import org.jasypt.digest.StandardStringDigester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lcg on 2015/7/17.
 */
public class Sha256Digester implements Digester {
    private Logger logger = LoggerFactory.getLogger(Sha256Digester.class);
    private StandardByteDigester byteSha256;
    private StandardStringDigester stringSha256;

    public Sha256Digester() {
        byteSha256 = new StandardByteDigester();
        byteSha256.setAlgorithm(Algorithm.SHA256.algorithm);
        byteSha256.setIterations(100000);
        byteSha256.setSaltSizeBytes(16);
        byteSha256.initialize();

        stringSha256 = new StandardStringDigester();
        stringSha256.setAlgorithm(Algorithm.SHA256.algorithm);
        stringSha256.setIterations(100000);
        stringSha256.setSaltSizeBytes(16);
        stringSha256.initialize();
    }

    @Override
    public byte[] digest(byte[] message) throws DigesterException {
        try {
            return byteSha256.digest(message);
        } catch (Exception ex) {
            logger.error(String.format("摘要[%s]异常", message), ex);
            throw new DigesterException("摘要异常", ex);
        }
    }

    @Override
    public String digest(String message) throws DigesterException {
        try {
            return stringSha256.digest(message);
        } catch (Exception ex) {
            logger.error(String.format("摘要[%s]异常", message), ex);
            throw new DigesterException("摘要异常", ex);
        }
    }

    @Override
    public boolean matches(byte[] message, byte[] digest) throws DigesterException {
        try {
            return byteSha256.matches(message, digest);
        } catch (Exception ex) {
            logger.error(String.format("摘要匹配[%s]异常", message), ex);
            throw new DigesterException("摘要匹配异常", ex);
        }
    }

    @Override
    public boolean matches(String message, String digest) throws DigesterException {
        try {
            return stringSha256.matches(message, digest);
        } catch (Exception ex) {
            logger.error(String.format("摘要匹配[%s]异常", message), ex);
            throw new DigesterException("摘要匹配异常", ex);
        }
    }
}
