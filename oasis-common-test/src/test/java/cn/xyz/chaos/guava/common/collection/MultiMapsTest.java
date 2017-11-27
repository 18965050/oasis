package cn.xyz.chaos.guava.common.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;

/**
 * MultiMap 单元测试
 * <p />
 * MultiMap 允许value为一个集合
 *
 * @author lvchenggang
 *
 */
public class MultiMapsTest {

    /**
     * {@link com.google.common.collect.ArrayListMultimap}测试
     */
    @Test
    public void testArrayListMultiMap() {
        ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("Foo", "1");
        multiMap.put("Foo", "2");
        multiMap.put("Foo", "3");
        List<String> expected = Lists.newArrayList("1", "2", "3");
        assertEquals(multiMap.get("Foo"), expected);
    }

    /**
     * {@link com.google.common.collect.ArrayListMultimap#size()}测试
     */
    @Test
    public void testArrayListMultiMapSize() {
        ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("Foo", "1");
        multiMap.put("Foo", "2");
        multiMap.put("Foo", "3");
        multiMap.put("Bar", "1");
        multiMap.put("Bar", "2");
        multiMap.put("Bar", "3");
        Collection<String> expected = Lists.newArrayList("1", "2", "3", "1", "2", "3");
        // 注意: 结果不是2
        assertThat(multiMap.size(), is(6));
        assertArrayEquals(multiMap.values().toArray(), expected.toArray());
    }

    /**
     * {@link com.google.common.collect.ArrayListMultimap#asMap()}测试
     */
    @Test
    public void testArrayListMultiMapSizeAsMap() {
        ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("Foo", "1");
        multiMap.put("Foo", "2");
        multiMap.put("Foo", "3");
        multiMap.put("Bar", "1");
        multiMap.put("Bar", "2");
        multiMap.put("Bar", "3");
        Map<String, Collection<String>> map = multiMap.asMap();
        assertThat(map.size(), is(2));
        map.get("Foo").remove("3");
        assertThat(multiMap.size(), is(5));
        multiMap.put("Baz", "1");
        assertThat(map.size(), is(3));
        map.get("Foo").add("4");
        assertThat(multiMap.size(), is(7));

    }

    /**
     * {@link com.google.common.collect.ArrayListMultimap#get(Object)}测试
     */
    @Test
    public void testArrayListMultimapSameKeyValue() {
        ArrayListMultimap<String, String> multiMap = ArrayListMultimap.create();
        multiMap.put("Bar", "1");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "2");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        List<String> expected = Lists.newArrayList("1", "3", "2", "3", "3");
        assertEquals(multiMap.get("Bar"), expected);
    }

    /**
     * {@link com.google.common.collect.HashMultimap}测试
     */
    @Test
    public void testHashMultiMapSameKeyValue() {
        HashMultimap<String, String> multiMap = HashMultimap.create();
        multiMap.put("Bar", "1");
        multiMap.put("Bar", "2");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        multiMap.put("Bar", "3");
        assertThat(multiMap.size(), is(3));
    }
}
