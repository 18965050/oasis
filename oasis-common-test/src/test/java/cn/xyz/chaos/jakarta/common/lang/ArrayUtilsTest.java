package cn.xyz.chaos.jakarta.common.lang;

import static org.hamcrest.CoreMatchers.is;

import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.ArrayUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class ArrayUtilsTest {

    /**
     * {@link org.apache.commons.lang3.ArrayUtils#toString()}测试
     */
    @Test
    public void testToString() {
        int[] intArray = new int[] { 2, 3, 4, 5, 6 };
        int[][] multiDimension = new int[][] { { 1, 2, 3 }, { 2, 3 }, { 5, 6, 7 } };
        String arrayExpect = "{2,3,4,5,6}";
        String multiDimensionExpect = "{{1,2,3},{2,3},{5,6,7}}";

        Assert.assertThat(arrayExpect, is(ArrayUtils.toString(intArray)));
        Assert.assertThat(multiDimensionExpect, is(ArrayUtils.toString(multiDimension)));

    }

    /**
     * {@link org.apache.commons.lang3.ArrayUtils#clone(int[])}测试
     */
    @Test
    public void testClone() {
        int[] array1 = { 1, 2, 3, 4, 5, 6 };
        int[] array2 = ArrayUtils.clone(array1);
        Assert.assertEquals(ArrayUtils.toString(array1), ArrayUtils.toString(array2));
        Assert.assertNotSame(array1, array2);

    }

    /**
     * {@link org.apache.commons.lang3.ArrayUtils#reverse(int[])}测试
     */
    @Test
    public void testReverse() {
        int[] array1 = { 1, 2, 3, 4, 5, 6 };
        ArrayUtils.reverse(array1);
        Assert.assertEquals("{6,5,4,3,2,1}", ArrayUtils.toString(array1));
    }

    /**
     * {@link org.apache.commons.lang3.ArrayUtils#contains(Object[], Object)}测试
     */
    @Test
    public void testContains() {
        String[] stringArray = { "Red", "Orange", "Blue", "Brown", "Red" };
        Assert.assertTrue(ArrayUtils.contains(stringArray, "Blue"));
    }

    /**
     * {@link org.apache.commons.lang3.ArrayUtils#indexOf(Object[], Object)}, <br />
     * {@link org.apache.commons.lang3.ArrayUtils#lastIndexOf(Object[], Object)} 测试
     */
    @Test
    public void testEleIndex() {
        String[] stringArray = { "Red", "Orange", "Blue", "Brown", "Red" };
        Assert.assertThat(ArrayUtils.indexOf(stringArray, "Orange"), is(1));
        Assert.assertThat(ArrayUtils.lastIndexOf(stringArray, "Red"), is(4));
    }

    /**
     * {@link org.apache.commons.lang3.ArrayUtils#toMap(Object[])}测试
     */
    @Test
    public void testToMap() {
        Object[] weightArray = new Object[][] { { "H", new Double(1.007) }, { "He", new Double(4.002) },
                { "Li", new Double(6.941) }, { "Be", new Double(9.012) }, { "B", new Double(10.811) },
                { "C", new Double(12.010) }, { "N", new Double(14.007) }, { "O", new Double(15.999) },
                { "F", new Double(18.998) }, { "Ne", new Double(20.180) } };
        Map map = ArrayUtils.toMap(weightArray);
        Assert.assertThat((Double) map.get("He"), is(4.002));

    }
}
