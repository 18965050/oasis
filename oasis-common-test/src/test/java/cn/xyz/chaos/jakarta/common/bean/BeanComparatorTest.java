package cn.xyz.chaos.jakarta.common.bean;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.beanutils.BeanComparator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;

/**
 * {@link org.apache.commons.beanutils.BeanComparator}单元测试
 *
 * @author lvchenggang
 *
 */
public class BeanComparatorTest {

    List<Author> authorList = new ArrayList<Author>();
    Comparator<String> nameComparator = new Comparator<String>() {
        public int compare(String o1, String o2) {
            return Integer.valueOf(o1.length()).compareTo(o2.length());
        };
    };

    @Before
    public void setup() {
        authorList.add(new Author("littlelv2001", "green"));
        authorList.add(new Author("lcg", "red"));
        authorList.add(new Author("lv", "blue"));
    }

    /**
     * 默认排序方式及自定义排序方式测试
     */
    @Test
    public void testCompare() {
        Collections.sort(authorList, new BeanComparator("name"));
        Assert.assertThat(authorList.get(0).toString(), is("lcg,red"));
        Assert.assertThat(authorList.get(1).toString(), is("littlelv2001,green"));
        Assert.assertThat(authorList.get(2).toString(), is("lv,blue"));

        Collections.sort(authorList, new BeanComparator("name", nameComparator));
        Assert.assertThat(authorList.get(0).toString(), is("lv,blue"));
        Assert.assertThat(authorList.get(1).toString(), is("lcg,red"));
        Assert.assertThat(authorList.get(2).toString(), is("littlelv2001,green"));
    }
}
