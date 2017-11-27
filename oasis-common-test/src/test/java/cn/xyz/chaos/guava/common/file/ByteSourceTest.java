package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.ByteStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;

/**
 * {@link com.google.common.io.ByteSource}单元测试
 * <p />
 * ByteSource对字节输入进行处理
 *
 * @author lvchenggang
 *
 */
public class ByteSourceTest {

    /**
     * {@link com.google.common.io.ByteSource#read()}测试
     *
     * @throws Exception
     */
    @Test
    public void createByteSourceFromFileTest() throws Exception {
        File f1 = new File("src/test/resources/guava/sample.pdf");
        ByteSource byteSource = Files.asByteSource(f1);
        byte[] readBytes = byteSource.read();
        assertThat(readBytes, is(Files.toByteArray(f1)));
    }

    /**
     * {@link com.google.common.io.ByteSource#read()}测试
     *
     * @throws Exception
     */
    @Test
    public void createByteSourceFromByteArray() throws Exception {
        /*
         * byte[] bytes = new byte[] { 0xF, 0xF, 0xF, 0x3, 0x3 }; final InputStream is = new
         * ByteArrayInputStream(bytes); ByteSource byteSource = ByteStreams .asByteSource(new
         * InputSupplier<InputStream>() {
         * @Override public InputStream getInput() throws IOException { return is; } }); byte[] readBytes =
         * byteSource.read(); assertThat(readBytes, is(bytes));
         */
    }

    /**
     * {@link com.google.common.io.ByteSource#slice(long, long)}测试
     *
     * @throws Exception
     */
    @Test
    public void createByteSliceTest() throws Exception {
        /*
         * byte[] bytes = new byte[] { 0xF, 0xF, 0xF, 0x3, 0x3, 0xA, 0xA, 0xA, 0xA, 0xA }; byte[] expectedSlice = new
         * byte[] { 0xA, 0xA, 0xA, 0xA, 0xA }; final InputStream is = new ByteArrayInputStream(bytes); ByteSource
         * byteSource = ByteStreams .asByteSource(new InputSupplier<InputStream>() {
         * @Override public InputStream getInput() throws IOException { return is; } }); ByteSource slice =
         * byteSource.slice(5, 10); assertThat(slice.read(), is(expectedSlice));
         */
    }

    /**
     * {@link com.google.common.io.ByteSource#copyTo(ByteSink)}测试
     *
     * @throws Exception
     */
    @Test
    public void copyToByteSinkTest() throws Exception {
        File dest = new File("src/test/resources/guava/sampleCompany.pdf");
        dest.deleteOnExit();
        File source = new File("src/test/resources/guava/sample.pdf");
        ByteSource byteSource = Files.asByteSource(source);
        ByteSink byteSink = Files.asByteSink(dest);
        byteSource.copyTo(byteSink);
        assertThat(Files.toByteArray(dest), is(Files.toByteArray(source)));
    }

}
