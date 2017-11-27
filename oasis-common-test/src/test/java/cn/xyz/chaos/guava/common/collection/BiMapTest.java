package cn.xyz.chaos.guava.common.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * {@link com.google.common.collect.BiMap}单元测试
 * <p />
 * BiMap 可以通过value获取key
 *
 * @author lvchenggang
 *
 */
public class BiMapTest {

    /**
     * {@link com.google.common.collect.BiMap#put(Object, Object)}测试
     *
     * @throws Exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBiMapSameValueDifferentKey() throws Exception {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.put("2", "Tom");
    }

    /**
     * {@link com.google.common.collect.BiMap#forcePut(Object, Object)}测试
     *
     * @throws Exception
     */
    @Test
    public void testBiMapForcePut() throws Exception {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.forcePut("2", "Tom");
        assertThat(biMap.containsKey("1"), is(false));
        assertThat(biMap.containsKey("2"), is(true));
    }

    /**
     * {@link com.google.common.collect.BiMap#inverse()}测试
     *
     * @throws Exception
     */
    @Test
    public void testBiMapInverse() throws Exception {
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("1", "Tom");
        biMap.put("2", "Harry");
        assertThat(biMap.get("1"), is("Tom"));
        assertThat(biMap.get("2"), is("Harry"));
        BiMap<String, String> inverseMap = biMap.inverse();
        assertThat(inverseMap.get("Tom"), is("1"));
        assertThat(inverseMap.get("Harry"), is("2"));
    }
}
