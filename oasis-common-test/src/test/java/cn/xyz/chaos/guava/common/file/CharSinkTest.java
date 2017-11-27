package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.Files;

/**
 * {@link com.google.common.io.CharSink}单元测试
 * <p />
 * CharSink对字符输出进行处理
 *
 * @author lvchenggang
 *
 */
public class CharSinkTest {

    CharSink charSink;
    File sinkFile;

    @Before
    public void setUp() {
        sinkFile = new File("src/test/resources/guava/sinkExample.txt");
        sinkFile.deleteOnExit();
        charSink = Files.asCharSink(sinkFile, Charsets.UTF_8);
    }

    /**
     * {@link com.google.common.io.CharSink#write(CharSequence)}测试
     *
     * @throws Exception
     */
    @Test
    public void stringToCharSinkTest() throws Exception {
        String string = "the quick brown fox jumps over the lazy dog";
        charSink.write(string);
        assertThat(Files.toString(sinkFile, Charsets.UTF_8), is(string));
    }

    /**
     * {@link com.google.common.io.CharSink#writeLines(Iterable)}测试
     *
     * @throws Exception
     */
    @Test
    public void stringListToCharSinkTest() throws Exception {
        List<String> list = Lists.newArrayList("the quick brown", "fox jumps over", "the lazy dog");
        /** 注意:这个代码在Unix机器上运行须将\r\n-->\n */
        String expectedContent = "the quick brown\r\nfox jumps over\r\nthe lazy dog\r\n";
        charSink.writeLines(list);
        assertThat(Files.toString(sinkFile, Charsets.UTF_8), is(expectedContent));
    }

    /**
     * {@link com.google.common.io.CharSink#writeFrom(Readable)}测试
     *
     * @throws Exception
     */
    @Test
    public void readerToCharSinkTest() throws Exception {
        File source = new File("src/test/resources/guava/sampleTextFileOne.txt");
        BufferedReader reader = Files.newReader(source, Charsets.UTF_8);
        charSink.writeFrom(reader);
        assertThat(Files.toString(sinkFile, Charsets.UTF_8), is(Files.toString(source, Charsets.UTF_8)));
    }
}
