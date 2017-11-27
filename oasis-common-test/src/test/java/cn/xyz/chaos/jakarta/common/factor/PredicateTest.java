package cn.xyz.chaos.jakarta.common.factor;

import junit.framework.Assert;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.AllPredicate;
import org.apache.commons.collections.functors.AndPredicate;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.collections.functors.InstanceofPredicate;
import org.apache.commons.collections.functors.NotNullPredicate;
import org.apache.commons.collections.functors.OnePredicate;
import org.apache.commons.collections.functors.OrPredicate;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;

/**
 * {@link org.apache.commons.collections.Predicate}单元测试
 * <p />
 * Predicate提供了Filter功能
 *
 * @author lvchenggang
 *
 */
public class PredicateTest {

    /**
     * 几种基本Predicate测试, 包括: <br />
     * <ui>
     * <li>{@link org.apache.commons.collections.functors.EqualPredicate}</li>
     * <li>{@link org.apache.commons.collections.functors.InstanceofPredicate}</li> </ui>
     */
    @Test
    public void testBasicPreidcate() {
        String name = "Tim";
        Predicate nameJohn = new EqualPredicate("John");
        Predicate nameTim = new EqualPredicate("Tim");
        Predicate instanceString = new InstanceofPredicate(String.class);
        Predicate instanceDouble = new InstanceofPredicate(Double.class);
        Assert.assertFalse(nameJohn.evaluate(name));
        Assert.assertTrue(nameTim.evaluate(name));
        Assert.assertTrue(instanceString.evaluate(name));
        Assert.assertFalse(instanceDouble.evaluate(name));
    }

    /**
     * 自定义Predicate测试
     */
    @Test
    public void testCustomPredicate() {
        Predicate authorPredicate = new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Author author = (Author) object;
                return author.getName().equals("lcg") && author.getFavoriteColor().equals("blue");
            }
        };

        Author author = new Author("lcg", "red");
        Assert.assertFalse(authorPredicate.evaluate(author));
    }

    /**
     * {@link org.apache.commons.collections.functors.AndPredicate}, <br />
     * {@link org.apache.commons.collections.functors.OrPredicate}, <br />
     * {@link org.apache.commons.collections.functors.AllPredicate}测试
     */
    @Test
    public void testCompositePredicate() {
        Predicate isTim = new EqualPredicate("Tim");
        Predicate notNull = NotNullPredicate.INSTANCE;
        Predicate isString = new InstanceofPredicate(String.class);
        Predicate andPredicate = new AndPredicate(isTim, isString);
        Predicate orPredicate = new OrPredicate(isTim, isString);
        Predicate allPredicate = new AllPredicate(new Predicate[] { isTim, notNull, isString });
        // XOR
        Predicate onePredicate = new OnePredicate(new Predicate[] { isTim, notNull, isString });
        Assert.assertFalse(andPredicate.evaluate("Tom"));
        Assert.assertTrue(orPredicate.evaluate("Tom"));
        Assert.assertFalse(allPredicate.evaluate("Tom"));
        Assert.assertFalse(onePredicate.evaluate("Hehe"));
    }
}
