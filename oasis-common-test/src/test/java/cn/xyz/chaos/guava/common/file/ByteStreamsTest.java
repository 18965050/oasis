package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.google.common.io.ByteStreams;

/**
 * {@link com.google.common.io.ByteStreams}单元测试
 * <p />
 * ByteStreams是InputStream和OutputStream的工具类
 *
 * @author lvchenggang
 *
 */
public class ByteStreamsTest {

    /**
     * {@link com.google.common.io.ByteStreams#limit(InputStream, long)}测试
     *
     * @throws Exception
     */
    @Test
    public void limitByteStreamTest() throws Exception {
        File binaryFile = new File("src/test/resources/guava/sample.pdf");
        BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(binaryFile));
        InputStream limitedInputStream = ByteStreams.limit(inputStream, 10);
        assertThat(limitedInputStream.available(), is(10));
        assertThat(inputStream.available(), is(218882));
    }
}
