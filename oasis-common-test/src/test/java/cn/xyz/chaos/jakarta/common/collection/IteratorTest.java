package cn.xyz.chaos.jakarta.common.collection;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.iterators.ArrayListIterator;
import org.apache.commons.collections.iterators.FilterIterator;
import org.apache.commons.collections.iterators.LoopingIterator;
import org.apache.commons.collections.iterators.UniqueFilterIterator;
import org.junit.Assert;
import org.junit.Test;

/**
 * commons-collection包中几种Iterator工具类单元测试
 *
 * @author lvchenggang
 *
 */
public class IteratorTest {

    /**
     * {@link org.apache.commons.collections.iterators.LoopingIterator}测试
     */
    @Test
    public void testLoopIterator() {
        List<String> list = new ArrayList<String>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        Iterator<String> iter = new LoopingIterator(list);
        Assert.assertThat(iter.next(), is("aaa"));
        Assert.assertThat(iter.next(), is("bbb"));
        Assert.assertThat(iter.next(), is("ccc"));
        Assert.assertThat(iter.next(), is("aaa"));
    }

    /**
     * {@link org.apache.commons.collections.iterators.ArrayListIterator}测试
     */
    @Test(expected = NoSuchElementException.class)
    public void testRangeSelect() {
        Iterator<String> iterator = new ArrayListIterator(new String[] { "A", "B", "C", "D", "E", "F" }, 3, 5);
        Assert.assertThat(iterator.next(), is("D"));
        Assert.assertThat(iterator.next(), is("E"));
        Assert.assertThat(iterator.next(), is("F"));
        Assert.fail("no such element for F");
    }

    /**
     * {@link org.apache.commons.collections.iterators.FilterIterator}测试
     */
    @Test
    public void testFilter() {
        Predicate isEven = new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Integer input = (Integer) object;
                return input % 2 == 0;
            }
        };

        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        Iterator<Integer> filterIter = new FilterIterator(list.iterator(), isEven);
        Assert.assertThat(filterIter.next(), is(2));
        Assert.assertThat(filterIter.next(), is(4));
        Assert.assertThat(filterIter.next(), is(6));
    }

    /**
     * {@link org.apache.commons.collections.iterators.UniqueFilterIterator}测试
     */
    @Test
    public void testUniqueFilter() {
        String[] medals = new String[] { "gold", "silver", "silver", "gold", "bronze" };
        List<String> medalsList = Arrays.asList(medals);
        Iterator<String> uniqueFilterIter = new UniqueFilterIterator(medalsList.iterator());
        Assert.assertThat(uniqueFilterIter.next(), is("gold"));
        Assert.assertThat(uniqueFilterIter.next(), is("silver"));
        Assert.assertThat(uniqueFilterIter.next(), is("bronze"));
    }

}
