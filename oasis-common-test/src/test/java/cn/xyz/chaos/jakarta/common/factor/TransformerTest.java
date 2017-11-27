package cn.xyz.chaos.jakarta.common.factor;

import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.NOPTransformer;
import org.apache.commons.collections.functors.NotPredicate;
import org.apache.commons.collections.functors.SwitchTransformer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;

/**
 * {@link org.apache.commons.collections.Transformer}单元测试
 * <p />
 * Transformer提供了对象转换的功能
 *
 * @author lvchenggang
 *
 */
public class TransformerTest {

    List authorList = new ArrayList();

    Transformer authorTransformer;

    Transformer trimTransformer;

    @Before
    public void setup() {
        authorList.add(new Author("littlelv2001", "green"));
        authorList.add(new Author("lcg", "red"));
        authorList.add(new Author("lv", "blue"));

        authorTransformer = new Transformer() {
            @Override
            public Object transform(Object input) {
                Author author = (Author) input;
                return author.getName() + " " + author.getFavoriteColor();
            }
        };
        trimTransformer = new Transformer() {

            @Override
            public Object transform(Object input) {
                String s = (String) input;
                return "chain:" + s;
            }
        };
    }

    /**
     * 基本转换测试
     */
    @Test
    public void testTransformer() {
        CollectionUtils.transform(authorList, authorTransformer);
        Assert.assertThat((String) authorList.get(0), is("littlelv2001 green"));
    }

    /**
     * {@link org.apache.commons.collections.functors.ChainedTransformer}测试
     */
    @Test
    public void testTransformerChain() {
        Transformer chainTransformer = new ChainedTransformer(new Transformer[] { authorTransformer, trimTransformer });
        CollectionUtils.transform(authorList, chainTransformer);
        Assert.assertThat((String) authorList.get(0), is("chain:littlelv2001 green"));
    }

    /**
     * {@link org.apache.commons.collections.functors.SwitchTransformer}测试
     */
    @Test
    public void testConditionalTransformer() {
        Transformer oddTransform = new Transformer() {
            public Object transform(Object input) {
                Integer number = (Integer) input;
                return number.intValue() * 2;
            }
        };

        Transformer evenTransform = new Transformer() {
            public Object transform(Object input) {
                Integer number = (Integer) input;
                return number.intValue() * 3;
            }
        };

        Predicate isEven = new Predicate() {
            public boolean evaluate(Object input) {
                Integer number = (Integer) input;
                return (number.intValue() % 2 == 0);
            }
        };

        Predicate isOdd = new NotPredicate(isEven);

        Transformer predicateTransformer = new SwitchTransformer(new Predicate[] { isOdd, isEven },
                new Transformer[] { oddTransform, evenTransform }, NOPTransformer.INSTANCE);
        Assert.assertThat((Integer) predicateTransformer.transform(Integer.valueOf(1)), is(2));
        Assert.assertThat((Integer) predicateTransformer.transform(Integer.valueOf(2)), is(6));
    }
}
