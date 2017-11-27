package cn.xyz.chaos.guava.common.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Collection;

import org.junit.Test;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Lists;

/**
 * Immutable集合单元测试
 *
 * @author lvchenggang
 *
 */
public class ImmutableCollectionsTest {

    /**
     * {@link com.google.common.collect.ImmutableMultimap}测试
     */
    @Test
    public void testCreateImmutableMap() {
        ImmutableMultimap<Integer, String> multimap = new ImmutableMultimap.Builder<Integer, String>().put(1, "Foo")
                .putAll(2, "Foo", "Bar", "Baz").putAll(4, "Huey", "Duey", "Luey").put(3, "Single").build();
        Collection<String> list = Lists.newArrayList("Single");
        assertThat(multimap.get(3), is(list));
        list = Lists.newArrayList("Huey", "Duey", "Luey");
        assertThat(multimap.get(4), is(list));

    }

    /**
     * {@link com.google.common.collect.ImmutableList}添加元素失败测试
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddImmutableCollection() {
        ImmutableList<String> immutableList = new ImmutableList.Builder<String>().add("foo").add("bar").build();
        immutableList.add("baz");
    }

    /**
     * {@link com.google.common.collect.ImmutableList}删除元素失败测试
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveImmuableCollection() {
        ImmutableList<String> immutableList = new ImmutableList.Builder<String>().add("foo").add("bar").build();
        immutableList.remove("bar");
    }

    /**
     * {@link com.google.common.collect.ImmutableList}修改元素测试
     */
    @Test
    public void testChangeObjectImmutableCollection() {
        MutableObject mutableObject = new MutableObject("initialState");
        ImmutableList<MutableObject> immutableList = new ImmutableList.Builder<MutableObject>().add(mutableObject)
                .build();
        assertThat(immutableList.get(0).getMutableProperty(), is("initialState"));
        immutableList.get(0).setMutableProperty("changedState");
        assertThat(immutableList.get(0).getMutableProperty(), is("changedState"));
    }

    private class MutableObject {
        private String mutableProperty;

        private MutableObject(String mutableProperty) {
            this.mutableProperty = mutableProperty;
        }

        private String getMutableProperty() {
            return mutableProperty;
        }

        private void setMutableProperty(String mutableProperty) {
            this.mutableProperty = mutableProperty;
        }
    }
}
