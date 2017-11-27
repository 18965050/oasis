package cn.xyz.chaos.jakarta.common.factor;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.commons.collections.comparators.FixedOrderComparator;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.junit.Assert;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;

/**
 * 集合包中几种Comparator工具类单元测试
 *
 * @author lvchenggang
 *
 */
public class ComparatorTest {

    /**
     * {@link org.apache.commons.collections.comparators.ReverseComparator}测试
     */
    @Test
    public void testReverseComparator() {
        Author author1 = new Author("littlelv2001", "green");
        Author author2 = new Author("lcg", "red");
        Comparator<Author> authorComparator = new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Comparator reverseComparator = new ReverseComparator(authorComparator);
        Assert.assertTrue(authorComparator.compare(author1, author2) > 0);
        Assert.assertTrue(reverseComparator.compare(author1, author2) < 0);
    }

    /**
     * {@link org.apache.commons.collections.comparators.ComparatorChain}测试
     */
    @Test
    public void testComparatorChain() {
        List<Author> authorList = new ArrayList<Author>();
        authorList.add(new Author("lcg", "red"));
        authorList.add(new Author("lcg", "green"));

        ComparatorChain comparatorChain = new ComparatorChain();
        comparatorChain.addComparator(new BeanComparator("name"));
        comparatorChain.addComparator(new BeanComparator("favoriteColor"));
        Collections.sort(authorList, comparatorChain);
        Assert.assertThat(authorList.get(0).getFavoriteColor(), is("green"));
    }

    /**
     * {@link org.apache.commons.collections.comparators.NullComparator}测试
     */
    @Test
    public void testNullComparator() {
        List<Author> authorList = new ArrayList<Author>();
        authorList.add(new Author("lcg", "red"));
        authorList.add(null);
        authorList.add(new Author("lcg", "green"));
        Comparator nameComparator = new BeanComparator("favoriteColor");
        Comparator nullComparator = new NullComparator(nameComparator);
        Collections.sort(authorList, nullComparator);
        Assert.assertNull(authorList.get(2));
        nullComparator = new NullComparator(nameComparator, false);
        Collections.sort(authorList, nullComparator);
        Assert.assertNull(authorList.get(0));
    }

    /**
     * {@link org.apache.commons.collections.comparators.FixedOrderComparator}测试
     */
    @Test
    public void testFixedOrderComparator() {
        String[] medalOrder = { "tin", "bronze", "silver", "gold", "platinum" };
        Comparator fixedOrderComparator = new FixedOrderComparator(medalOrder);
        Arrays.sort(medalOrder);
        Assert.assertThat(medalOrder, is(new String[] { "bronze", "gold", "platinum", "silver", "tin" }));
        Arrays.sort(medalOrder, fixedOrderComparator);
        Assert.assertThat(medalOrder, is(new String[] { "tin", "bronze", "silver", "gold", "platinum" }));
    }
}
