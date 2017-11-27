package cn.xyz.chaos.jakarta.common.collection;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.list.PredicatedList;
import org.junit.Test;

/**
 * List工具类单元测试
 *
 * @author lvchenggang
 *
 */
public class ListTest {

    /**
     * {@link org.apache.commons.collections.list.PredicatedList}测试
     */
    @Test(expected = IllegalArgumentException.class)
    public void testFilter() {
        Predicate isEven = new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Integer input = (Integer) object;
                return input % 2 == 0;
            }
        };
        List<Integer> list = PredicatedList.decorate(new ArrayList<Integer>(), isEven);
        list.add(Integer.valueOf(1));
        list.add(Integer.valueOf(2));
        list.add(Integer.valueOf(3));
        list.add(Integer.valueOf(4));

    }
}
