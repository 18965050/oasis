package cn.xyz.chaos.jakarta.common.bean;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.functors.AllPredicate;
import org.apache.commons.collections.functors.EqualPredicate;
import org.apache.commons.collections.functors.NotNullPredicate;
import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;
import cn.xyz.chaos.jakarta.common.model.Book;
import cn.xyz.chaos.jakarta.common.model.Chapter;

/**
 * {@link org.apache.commons.beanutils.BeanPredicate}单元测试
 *
 * @author lvchenggang
 *
 */
public class BeanPredicateTest {

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
     * 组合Predicate方式测试
     */
    @Test
    public void testValidate() {
        Predicate nameNotNullPredicate = new BeanPredicate("name", NotNullPredicate.INSTANCE);
        Predicate favoriteColorPredicate = new BeanPredicate("author.favoriteColor",
                EqualPredicate.getInstance("blue"));
        Predicate allPredicate = new AllPredicate(new Predicate[] { nameNotNullPredicate, favoriteColorPredicate });
        Assert.assertTrue(allPredicate.evaluate(book));
    }

}
