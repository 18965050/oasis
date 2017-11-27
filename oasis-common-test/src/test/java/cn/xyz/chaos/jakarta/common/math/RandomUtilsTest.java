package cn.xyz.chaos.jakarta.common.math;

import junit.framework.Assert;

import org.apache.commons.lang.math.RandomUtils;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang.math.RandomUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class RandomUtilsTest {

    @Test
    public void testRandom() {
        int number = RandomUtils.nextInt(100);
        Assert.assertTrue(number < 100 && number >= 0);
    }
}
