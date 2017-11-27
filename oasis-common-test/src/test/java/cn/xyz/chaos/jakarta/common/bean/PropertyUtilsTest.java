package cn.xyz.chaos.jakarta.common.bean;

import static org.hamcrest.CoreMatchers.is;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;
import cn.xyz.chaos.jakarta.common.model.Book;
import cn.xyz.chaos.jakarta.common.model.Chapter;

/**
 * {@link org.apache.commons.beanutils.PropertyUtils}单元测试
 * <p />
 * PropertyUtils提供了对Bean的属性进行快速方便访问的手段
 *
 * @author lvchenggang
 *
 */
public class PropertyUtilsTest {

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
     * {@link org.apache.commons.beanutils.PropertyUtils#getSimpleProperty(Object, String)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testGetSimpleProperty() throws Exception {
        Assert.assertThat((String) PropertyUtils.getSimpleProperty(author, "name"), is("lcg"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#getNestedProperty(Object, String)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testGetNestProperty() throws Exception {
        Assert.assertThat((String) PropertyUtils.getNestedProperty(book, "author.name"), is("lcg"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#getIndexedProperty(Object, String)} , <br />
     * {@link org.apache.commons.beanutils.PropertyUtils#getIndexedProperty(Object, String, int)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testGetIndexProperty() throws Exception {
        Chapter chapter1 = (Chapter) PropertyUtils.getIndexedProperty(book, "chapters[0]");
        Chapter chapter2 = (Chapter) PropertyUtils.getIndexedProperty(book, "chapters", 0);
        Assert.assertThat(chapter1.getTitle(), is("Good Habbit"));
        Assert.assertThat(chapter2.getTitle(), is("Good Habbit"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#getMappedProperty(Object, String)} , <br />
     * {@link org.apache.commons.beanutils.PropertyUtils#getMappedProperty(Object, String, String)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testGetMapProperty() throws Exception {
        Assert.assertThat((String) PropertyUtils.getMappedProperty(chapter, "sections(first)"),
                is("eat after washing hands"));
        Assert.assertThat((String) PropertyUtils.getMappedProperty(chapter, "sections", "second"),
                is("early sleep early getup"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#getProperty(Object, String)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testGetProperty() throws Exception {
        Assert.assertThat((String) PropertyUtils.getProperty(book, "chapters[0].sections(first)"),
                is("eat after washing hands"));
        Assert.assertThat((String) PropertyUtils.getProperty(book, "author.favoriteColor"), is("blue"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#getPropertyType(Object, String)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testGetPropertyType() throws Exception {
        Assert.assertThat(PropertyUtils.getPropertyType(book, "author").getSimpleName(), is("Author"));
        Assert.assertThat(PropertyUtils.getPropertyType(book, "chapters").getSimpleName(), is("List"));

    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#copyProperties(Object, Object)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testCopyProperty() throws Exception {
        Book book2 = new Book();
        PropertyUtils.copyProperties(book2, book);
        Assert.assertThat(book2.getName(), is("How-to-be A Good Man"));
        Assert.assertThat(book2.getAuthor().getFavoriteColor(), is("blue"));
        Assert.assertThat(book2.getChapters().get(0).getTitle(), is("Good Habbit"));
        Assert.assertThat(book2.getChapters().get(0).getSections().get("second"), is("early sleep early getup"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#setProperty(Object, String, Object)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testSetProperty() throws Exception {
        PropertyUtils.setProperty(chapter, "title", "BadHabbit");
        PropertyUtils.setProperty(chapter, "sections(first)", "eat before washing hands");
        Assert.assertThat(chapter.getTitle(), is("BadHabbit"));
        Assert.assertThat((String) PropertyUtils.getProperty(chapter, "sections(first)"),
                is("eat before washing hands"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#isReadable(Object, String)} , <br />
     * {@link org.apache.commons.beanutils.PropertyUtils#isWriteable(Object, String)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testReadableWritable() throws Exception {
        Assert.assertTrue(PropertyUtils.isReadable(book, "name"));
        Assert.assertTrue(PropertyUtils.isReadable(chapter, "title"));
        Assert.assertTrue(PropertyUtils.isWriteable(book, "name"));
        Assert.assertTrue(PropertyUtils.isWriteable(chapter, "title"));
    }

    /**
     * {@link org.apache.commons.beanutils.PropertyUtils#describe(Object)}测试 <br />
     * describe会将bean转换为一个Map对象
     *
     * @throws Exception
     */
    @Test
    public void testDescribe() throws Exception {
        Map bookMap = PropertyUtils.describe(book);
        Assert.assertThat((String) bookMap.get("name"), is("How-to-be A Good Man"));
    }
}
