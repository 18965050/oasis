package cn.xyz.chaos.guava.common.collection;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cn.xyz.chaos.guava.common.model.Person;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Range;

/**
 * {@link com.google.common.collect.Range}单元测试
 *
 * @author lvchenggang
 *
 */
public class RangeTest {

    /**
     * {@link com.google.common.collect.Range#openClosed(Comparable, Comparable)} 测试
     */
    @Test
    public void testRangeOpenClosed() {
        Range<Integer> numberRange = Range.openClosed(1, 10);
        assertThat(numberRange.contains(5), is(true));
        assertThat(numberRange.contains(10), is(true));
        assertThat(numberRange.contains(1), is(false));
    }

    /**
     * Range,Predicate,Function结合使用测试
     */
    @Test
    public void testPersonAgeRange() {
        Person person = new Person("Art", "Vandelay", 37, "M");
        Person person1 = new Person("Johnny", "Quest", 14, "M");
        Person person2 = new Person("Fred", "Flinstone", 50, "M");
        Range<Integer> ageRange = Range.closed(35, 50);

        Function<Person, Integer> ageFunction = new Function<Person, Integer>() {
            @Override
            public Integer apply(Person person) {
                return person.getAge();
            }
        };

        Predicate<Person> predicate = Predicates.compose(ageRange, ageFunction);
        assertThat(predicate.apply(person), is(true));
        assertThat(predicate.apply(person1), is(false));
        assertThat(predicate.apply(person2), is(true));

    }

}
