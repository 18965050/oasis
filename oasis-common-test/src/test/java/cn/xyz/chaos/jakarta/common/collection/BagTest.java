package cn.xyz.chaos.jakarta.common.collection;

import static org.hamcrest.CoreMatchers.is;

import org.apache.commons.collections.Bag;
import org.apache.commons.collections.HashBag;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.collections.Bag}单元测试
 * <p />
 * Bag继承自Collection,提供了在集合中存放一个对象的多个拷贝及获取对象数量的方法
 *
 * @author lvchenggang
 *
 */
public class BagTest {

    /**
     * {@link org.apache.commons.collections.Bag#add(Object, int)}, <br />
     * {@link org.apache.commons.collections.Bag#remove(Object, int)}, <br />
     * {@link org.apache.commons.collections.Bag#getCount(Object)}测试
     */
    @Test
    public void testHashBag() {
        Bag bag = new HashBag();
        bag.add("TEST1", 100);
        bag.add("TEST2", 500);
        Assert.assertThat(bag.getCount("TEST1"), is(100));
        Assert.assertThat(bag.getCount("TEST2"), is(500));
        bag.remove("TEST1", 1);
        bag.remove("TEST2", 50);
        Assert.assertThat(bag.getCount("TEST1"), is(99));
        Assert.assertThat(bag.getCount("TEST2"), is(450));
    }

}
