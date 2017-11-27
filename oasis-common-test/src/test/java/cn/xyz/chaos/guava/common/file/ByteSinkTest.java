package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;

import com.google.common.io.ByteSink;
import com.google.common.io.Files;

/**
 * {@link com.google.common.io.ByteSink}单元测试
 * <p />
 * ByteSink对字节输出进行处理
 *
 * @author lvchenggang
 *
 */
public class ByteSinkTest {

    ByteSink byteSink;

    /**
     * 根据byte[]实现文件写
     *
     * @throws Exception
     */
    @Test
    public void testCreateFileByteSink() throws Exception {
        File dest = new File("src/test/resources/guava/byteSink.pdf");
        dest.deleteOnExit();
        byteSink = Files.asByteSink(dest);
        File file = new File("src/test/resources/guava/sample.pdf");
        byteSink.write(Files.toByteArray(file));
        assertThat(Files.toByteArray(dest), is(Files.toByteArray(file)));
    }

    /**
     * 根据inputStream实现文件写
     *
     * @throws Exception
     */
    @Test
    public void testWriteFromInputStream() throws Exception {
        File dest = new File("src/test/resources/guava/byteSink.pdf");
        dest.deleteOnExit();
        byteSink = Files.asByteSink(dest);
        File sourceFile = new File("src/test/resources/guava/sample.pdf");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(sourceFile));
        byteSink.writeFrom(inputStream);
        assertThat(Files.toByteArray(dest), is(Files.toByteArray(sourceFile)));
    }
}
