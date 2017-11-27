package cn.xyz.chaos.jakarta.common.collection;

import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.collections.CollectionUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class CollectionUtilsTest {

    /**
     * {@link org.apache.commons.collections.CollectionUtils#countMatches(java.util.Collection, Predicate)} , <br />
     * {@link org.apache.commons.collections.CollectionUtils#cardinality(Object, java.util.Collection)} 测试
     */
    @Test
    public void testCount() {
        String[] array = new String[] { "A", "B", "C", "C", "B", "B" };
        List stringList = Arrays.asList(array);
        Predicate equalPredicate = new EqualPredicate("C");
        Assert.assertThat(CollectionUtils.countMatches(stringList, equalPredicate), is(2));
        Assert.assertThat(CollectionUtils.cardinality("B", stringList), is(3));
    }

    /**
     * 并,交,差,补集测试
     */
    @Test
    public void testSetOperation() {
        String[] arrayA = new String[] { "1", "2", "3", "3", "4", "5" };
        String[] arrayB = new String[] { "3", "4", "4", "5", "6", "7" };

        List<String> a = Arrays.asList(arrayA);
        List<String> b = Arrays.asList(arrayB);

        List<String> union = (List<String>) CollectionUtils.union(a, b); // 并集
        List<String> intersection = (List<String>) CollectionUtils.intersection(a, b); // 交集
        List<String> disjunction = (List<String>) CollectionUtils.disjunction(a, b); // 析取
        List<String> subtract = (List<String>) CollectionUtils.subtract(a, b); // 差集

        Collections.sort(union);
        Collections.sort(intersection);
        Collections.sort(disjunction);
        Collections.sort(subtract);

        Assert.assertThat(ArrayUtils.toString(union.toArray()), is("{1,2,3,3,4,4,5,6,7}"));
        Assert.assertThat(ArrayUtils.toString(intersection.toArray()), is("{3,4,5}"));
        Assert.assertThat(ArrayUtils.toString(disjunction.toArray()), is("{1,2,3,4,6,7}"));
        Assert.assertThat(ArrayUtils.toString(subtract.toArray()), is("{1,2,3}"));

    }
}
