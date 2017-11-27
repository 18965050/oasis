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
public class Md5Digester implements Digester {
    private Logger logger = LoggerFactory.getLogger(Sha256Digester.class);
    private StandardByteDigester byteMd5;
    private StandardStringDigester stringMd5;

    public Md5Digester() {
        byteMd5 = new StandardByteDigester();
        byteMd5.setAlgorithm(Algorithm.MD5.algorithm);
        byteMd5.initialize();

        stringMd5 = new StandardStringDigester();
        stringMd5.setAlgorithm(Algorithm.MD5.algorithm);
        stringMd5.initialize();
    }

    @Override
    public byte[] digest(byte[] message) throws DigesterException {
        try {
            return byteMd5.digest(message);
        } catch (Exception ex) {
            logger.error(String.format("摘要[%s]异常", message), ex);
            throw new DigesterException("摘要异常", ex);
        }
    }

    @Override
    public String digest(String message) throws DigesterException {
        try {
            return stringMd5.digest(message);
        } catch (Exception ex) {
            logger.error(String.format("摘要[%s]异常", message), ex);
            throw new DigesterException("摘要异常", ex);
        }
    }

    @Override
    public boolean matches(byte[] message, byte[] digest) throws DigesterException {
        try {
            return byteMd5.matches(message, digest);
        } catch (Exception ex) {
            logger.error(String.format("摘要匹配[%s]异常", message), ex);
            throw new DigesterException("摘要匹配异常", ex);
        }
    }

    @Override
    public boolean matches(String message, String digest) throws DigesterException {
        try {
            return stringMd5.matches(message, digest);
        } catch (Exception ex) {
            logger.error(String.format("摘要匹配[%s]异常", message), ex);
            throw new DigesterException("摘要匹配异常", ex);
        }
    }
}
