package cn.xyz.chaos.jakarta.common.lang;

import junit.framework.Assert;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.time.StopWatch}单元测试
 * <p />
 * StopWatch是个很方便的计时工具类.Spring中也有一个类似的StopWatch
 *
 * @see org.springframework.util.StopWatch
 *
 *
 * @author lvchenggang
 *
 */
public class StopWatchTest {

    private StopWatch clock = new StopWatch();

    /**
     * {@link org.apache.commons.lang3.time.StopWatch}简单测试
     */
    @Test
    public void test() {
        clock.start();
        for (int i = 0; i < 1000000; i++) {
            Math.sin(0.34);
        }
        clock.stop();
        long time1 = clock.getTime();
        clock.reset();
        clock.start();
        for (int i = 0; i < 10000000; i++) {
            Math.sin(0.34);
        }
        clock.stop();
        long time2 = clock.getTime();
        Assert.assertTrue(time1 < time2);
    }

}
