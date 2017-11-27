package cn.xyz.chaos.jakarta.common.math;

import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.math.FloatRange;
import org.apache.commons.lang.math.IntRange;
import org.apache.commons.lang.math.Range;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang.math.Range}单元测试
 *
 * @author lvchenggang
 *
 */
public class RangeTest {

    @Test
    public void testRange() {
        Range doubleRange = new DoubleRange(0.0, 0.65);
        Range intRange = new IntRange(1, 10);
        Range floatRange = new FloatRange(1.0f, 5.1f);
        Assert.assertTrue(doubleRange.containsDouble(0.3));
        Assert.assertFalse(intRange.containsInteger(12));
        Assert.assertTrue(floatRange.containsFloat(3.0f));
    }

}
