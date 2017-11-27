package cn.xyz.chaos.common.utils;

import org.junit.Assert;
import org.junit.Test;

import cn.xyz.chaos.common.utils.digest.Digester;
import cn.xyz.chaos.common.utils.digest.Digests2Utils;

/**
 * Created by lcg on 2015/7/16.
 */
public class DigestsUtilsTest {
    @Test
    public void matches() {
        String message = "ins1234";
        for (int i = 0; i < 100; i++) {
            String digest = Digests2Utils.digest(message, Digester.Algorithm.MD5);
            System.out.println(String.format("md5[%d] %s", i, digest));
            Assert.assertTrue(Digests2Utils.matches(message, digest, Digester.Algorithm.MD5));
        }
        Assert.assertTrue(Digests2Utils.matches(message, "u9iwh+LtTyNVps9SYcjzy8pEYtGaMXw2", Digester.Algorithm.MD5));
    }

    @Test
    public void matchesSha256() {
        String message = "ins1234";
        for (int i = 0; i < 100; i++) {
            String digest = Digests2Utils.digest(message, Digester.Algorithm.SHA256);
            System.out.println(String.format("sha256[%d] %s", i, digest));
            Assert.assertTrue(Digests2Utils.matches(message, digest, Digester.Algorithm.SHA256));
        }
        // Assert.assertTrue(Digests2Utils.matches(message, "u9iwh+LtTyNVps9SYcjzy8pEYtGaMXw2",
        // DigestsUtils.Algorithm.MD5));
    }
}
