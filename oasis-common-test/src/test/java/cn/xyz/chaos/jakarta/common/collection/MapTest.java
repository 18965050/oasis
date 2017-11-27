package cn.xyz.chaos.jakarta.common.collection;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.BidiMap;
import org.apache.commons.collections.MultiHashMap;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.bidimap.DualHashBidiMap;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.collections.functors.InstanceofPredicate;
import org.apache.commons.collections.functors.OrPredicate;
import org.apache.commons.collections.map.CaseInsensitiveMap;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.collections.map.LazyMap;
import org.apache.commons.collections.map.PredicatedMap;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Map工具类单元测试
 *
 * @author lvchenggang
 *
 */
public class MapTest {

    /**
     * {@link org.apache.commons.collections.MultiHashMap}测试
     */
    @Test
    public void testMultiMap() {
        MultiMap map = new MultiHashMap();
        map.put("ONE", "TEST");
        map.put("ONE", "HELLO");
        map.put("TWO", "TESTIMONY");
        map.put("TWO", "PICNIC");
        List<String> list1 = (List<String>) map.get("ONE");
        List<String> list2 = (List<String>) map.get("TWO");
        Assert.assertThat(list1.get(0), is("TEST"));
        Assert.assertThat(list1.get(1), is("HELLO"));
        Assert.assertThat(list2.get(0), is("TESTIMONY"));
        Assert.assertThat(list2.get(1), is("PICNIC"));
    }

    /**
     * {@link org.apache.commons.collections.BidiMap}测试
     */
    @Test
    public void testBidimap() {
        BidiMap bidiMap = new DualHashBidiMap();
        bidiMap.put("il", "Illinois");
        bidiMap.put("az", "Arizona");
        bidiMap.put("va", "Virginia");
        Assert.assertThat((String) bidiMap.inverseBidiMap().get("Virginia"), is("va"));

    }

    /**
     * {@link org.apache.commons.collections.map.CaseInsensitiveMap}测试
     */
    @Test
    public void testCaseInsensitive() {
        Map grades = new CaseInsensitiveMap();
        grades.put("Fortney", "B-");
        grades.put("Puckett", "D+");
        grades.put("Flatt", "A-");
        Assert.assertThat((String) grades.get("puckett"), is("D+"));
    }

    /**
     * {@link org.apache.commons.collections.map.PredicatedMap}测试
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFilter() {
        Predicate onlyStrings = new InstanceofPredicate(String.class);
        Predicate onlyGreen = new EqualPredicate("green");
        Predicate onlyRed = new EqualPredicate("red");
        Predicate greenOrRed = new OrPredicate(onlyGreen, onlyRed);
        Map map = PredicatedMap.decorate(new HashMap(), onlyStrings, greenOrRed);
        map.put("lcg", "blue");
        map.put("lvchenggang", "red");
        map.put("littlelv2001", "black");
    }

    /**
     * {@link org.apache.commons.collections.map.LRUMap}测试
     */
    @Test
    public void testLRU() {
        Map<String, String> cache = new LRUMap(3);
        cache.put("1", "aaa");
        cache.put("2", "bbb");
        cache.put("3", "ccc");
        cache.get("2");
        cache.get("3");
        cache.put("4", "ddd");
        Assert.assertFalse(cache.containsKey("1"));
    }

    /**
     * {@link org.apache.commons.collections.map.LazyMap}测试
     */
    @Test
    public void testLazy() {
        Transformer reverse = new Transformer() {
            @Override
            public Object transform(Object input) {
                String str = (String) input;
                return StringUtils.reverse(str);
            }
        };
        Map<String, String> reverseMap = LazyMap.decorate(new HashMap<String, String>(), reverse);
        /** transformer until get() method called */
        Assert.assertThat((String) reverseMap.get("abc"), is("cba"));
        Assert.assertThat((String) reverseMap.get("def"), is("fed"));
    }
}
