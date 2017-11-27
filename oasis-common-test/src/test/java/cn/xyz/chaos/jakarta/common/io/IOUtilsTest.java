package cn.xyz.chaos.jakarta.common.io;

import static org.hamcrest.CoreMatchers.is;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.CountingOutputStream;
import org.apache.commons.io.output.TeeOutputStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.io.IOUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class IOUtilsTest {

    /**
     * {@link org.apache.commons.io.IOUtils}基本测试
     *
     * @throws Exception
     */
    @Test
    public void testBasic() throws Exception {
        InputStream is = this.getClass().getResourceAsStream("/jakarta/user.properties");
        String content = IOUtils.toString(is);
        IOUtils.closeQuietly(is);
        Assert.assertThat(content, is("name=Jarrod Hood\nspeed=5000"));
    }

    /**
     * {@link org.apache.commons.io.output.CountingOutputStream}测试
     *
     * @throws Exception
     */
    @Test
    public void testStreamCounting() throws Exception {
        File file = new File("test.txt");
        file.deleteOnExit();
        OutputStream os = new FileOutputStream(file);
        CountingOutputStream cos = new CountingOutputStream(os);
        cos.write("Hello".getBytes());
        Assert.assertThat(cos.getByteCount(), is(5L));
        IOUtils.closeQuietly(os);
        IOUtils.closeQuietly(cos);
    }

    /**
     * {@link org.apache.commons.io.output.TeeOutputStream}测试
     *
     * @throws Exception
     */
    @Test
    public void testSplitOutput() throws Exception {
        File output1 = new File("test1.txt");
        File output2 = new File("test2.txt");
        output1.deleteOnExit();
        output2.deleteOnExit();
        OutputStream os1 = new FileOutputStream(output1);
        OutputStream os2 = new FileOutputStream(output2);
        OutputStream teeOs = new TeeOutputStream(os1, os2);
        teeOs.write("Hello".getBytes());
        teeOs.flush();
        IOUtils.closeQuietly(teeOs);
        IOUtils.closeQuietly(os1);
        IOUtils.closeQuietly(os2);
    }
}
