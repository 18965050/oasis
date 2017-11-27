package cn.xyz.chaos.jakarta.common.factor;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.ChainedClosure;
import org.apache.commons.collections.functors.IfClosure;
import org.apache.commons.collections.functors.WhileClosure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;
import cn.xyz.chaos.jakarta.common.model.Book;
import cn.xyz.chaos.jakarta.common.model.Car;
import cn.xyz.chaos.jakarta.common.model.Chapter;

/**
 * {@link org.apache.commons.collections.Closure}单元测试
 * <p />
 * 我们可以将Closure看做一个CallBack函数
 *
 * @author lvchenggang
 *
 */
public class ClosureTest {

    private Author author;
    private Chapter chapter;
    private Book book;

    @Before
    public void setup() {
        author = new Author("lcg", "blue");
        Map<String, String> map = new HashMap<String, String>();
        chapter = new Chapter("Good Habbit");
        chapter.addSection("first", "eat after washing hands");
        chapter.addSection("second", "early sleep early getup");
        book = new Book("How-to-be A Good Man", author);
        book.addChapter(chapter);
    }

    /**
     * {@link org.apache.commons.collections.Closure}简单测试
     */
    @Test
    public void testSimpleClosure() {
        Closure closure = new Closure() {
            @Override
            public void execute(Object input) {
                Author author = (Author) input;
                author.setName("lvchenggang");
            }
        };
        closure.execute(author);
        Assert.assertThat(author.getName(), is("lvchenggang"));
    }

    /**
     * {@link org.apache.commons.collections.functors.ChainedClosure}测试
     */
    @Test
    public void testChainedClosure() {
        Closure nameChange = new Closure() {
            @Override
            public void execute(Object input) {
                Author author = (Author) input;
                author.setName("lvchenggang");
            }
        };
        Closure favorChange = new Closure() {
            @Override
            public void execute(Object input) {
                Author author = (Author) input;
                author.setFavoriteColor("red");
            }
        };

        Closure chainedClosure = ChainedClosure.getInstance(new Closure[] { nameChange, favorChange });
        chainedClosure.execute(author);
        Assert.assertThat(author.getName(), is("lvchenggang"));
        Assert.assertThat(author.getFavoriteColor(), is("red"));
    }

    /**
     * {@link org.apache.commons.collections.functors.IfClosure}测试
     */
    @Test
    public void testIfClosure() {
        Closure favorRed = new Closure() {
            @Override
            public void execute(Object input) {
                Author author = (Author) input;
                author.setFavoriteColor("red");
            }
        };

        Closure favorYellow = new Closure() {
            @Override
            public void execute(Object input) {
                Author author = (Author) input;
                author.setFavoriteColor("yellow");
            }
        };

        Predicate isMe = new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Author author = (Author) object;
                return author.getName().equals("lcg");
            }
        };

        Closure closure = new IfClosure(isMe, favorRed, favorYellow);
        closure.execute(author);
        Assert.assertThat(author.getFavoriteColor(), is("red"));

    }

    /**
     * {@link org.apache.commons.collections.functors.WhileClosure}测试 <br />
     * 请注意WhileClosure支持while和do...while两种方式
     */
    @Test
    public void testWhileClosure() {
        Closure drive = new Closure() {
            @Override
            public void execute(Object input) {
                Car car = (Car) input;
                car.setFuel(car.getFuel() - 1);
                car.setMile(car.getMile() + 10);
            }
        };

        Predicate hasFuel = new Predicate() {
            @Override
            public boolean evaluate(Object object) {
                Car car = (Car) object;
                return car.getFuel() > 0;
            }
        };
        Closure closure = WhileClosure.getInstance(hasFuel, drive, false);
        Car car = new Car();
        closure.execute(car);
        Assert.assertThat(car.getMile(), is(50));
    }
}
