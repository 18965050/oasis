package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

/**
 * {@link com.google.common.io.Files}单元测试
 *
 * @author lvchenggang
 *
 */
public class FilesTest {

    /**
     * {@link com.google.common.io.Files#write(CharSequence, File, java.nio.charset.Charset)} , <br />
     * {@link com.google.common.io.Files#append(CharSequence, File, java.nio.charset.Charset)} <br />
     * 测试
     *
     * @throws IOException
     */
    @Test
    public void appendingWritingToFileTest() throws IOException {
        File file = new File("src/test/resources/guava/quote.txt");
        file.deleteOnExit();

        String hamletQuoteStart = "To be, or not to be";
        Files.write(hamletQuoteStart, file, Charsets.UTF_8);
        assertThat(Files.toString(file, Charsets.UTF_8), is(hamletQuoteStart));

        String hamletQuoteEnd = ",that is the question";
        Files.append(hamletQuoteEnd, file, Charsets.UTF_8);
        assertThat(Files.toString(file, Charsets.UTF_8), is(hamletQuoteStart + hamletQuoteEnd));

        String overwrite = "Overwriting the file";
        Files.write(overwrite, file, Charsets.UTF_8);
        assertThat(Files.toString(file, Charsets.UTF_8), is(overwrite));
    }

    /**
     * 文件拷贝测试
     *
     * @throws IOException
     */
    @Test
    public void writeBytesToFileTest() throws IOException {
        File file = new File("src/test/resources/guava/sampleTextFileOne.txt");
        File testFile = new File("src/test/resources/guava/testOutput.txt");
        testFile.deleteOnExit();

        byte[] bytes = Files.toByteArray(file);
        Files.write(bytes, testFile);

        assertThat(Files.equal(file, testFile), is(true));

    }

}
