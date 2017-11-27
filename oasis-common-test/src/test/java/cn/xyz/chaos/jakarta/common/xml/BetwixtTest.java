package cn.xyz.chaos.jakarta.common.xml;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.commons.betwixt.io.BeanWriter;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import cn.xyz.chaos.jakarta.common.model.Author;

/**
 * commons-betwixt 单元测试
 * <p />
 * commons-betwist是另一个XML和JavaBean之间转换的工具
 *
 * @author lvchenggang
 *
 */
public class BetwixtTest {

    /**
     * JavaBean转换为XML测试
     *
     * @throws Exception
     */
    @Test
    public void testObject2XML() throws Exception {
        Author author = new Author("lcg", "blue");
        File f = new File("output.xml");

        f.deleteOnExit();
        OutputStream ops = new FileOutputStream(f);
        BeanWriter beanWriter = new BeanWriter(ops);

        beanWriter.enablePrettyPrint();
        beanWriter.setEndOfLine("\r\n");
        beanWriter.setIndent("\t");
        beanWriter.write(author);
        String output = FileUtils.readFileToString(f);

        Assert.assertThat(output, is(
                "\t<Author id=\"1\">\r\n\t\t<favoriteColor>blue</favoriteColor>\r\n\t\t<name>lcg</name>\r\n\t</Author>\r\n"));
        IOUtils.closeQuietly(ops);

    }
}
