package cn.xyz.chaos.jakarta.common.math;

import static org.hamcrest.CoreMatchers.is;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang.math.NumberUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class NumberUtilsTest {

    @Test
    public void testMinMax() {
        double[] array = { 0.2, 0.4, 0.5, -3.0, 4.223, 4.226 };
        Assert.assertThat(NumberUtils.max(array), is(4.226));
        Assert.assertThat(NumberUtils.min(array), is(-3.0));
    }
}
