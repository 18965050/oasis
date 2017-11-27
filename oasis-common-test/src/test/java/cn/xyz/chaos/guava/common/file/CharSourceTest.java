package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;

/**
 * {@link com.google.common.io.CharSource}单元测试
 * <p />
 * CharSource对字符输入进行处理
 *
 * @author lvchenggang
 *
 */
public class CharSourceTest {

    private CharSource charSource;

    @Before
    public void setUp() {
        File sourceFile = new File("src/test/resources/guava/lines.txt");
        charSource = Files.asCharSource(sourceFile, Charsets.UTF_8);
    }

    /**
     * {@link com.google.common.io.CharSource#read()}测试
     *
     * @throws Exception
     */
    @Test
    public void charSourceReadStringTest() throws Exception {
        String expectedContent = "The quick brown\nfox jumps over\nthe lazy dog";
        String readString = charSource.read();
        assertThat(readString, is(expectedContent));
    }

    /**
     * {@link com.google.common.io.CharSource#readLines()}测试
     *
     * @throws Exception
     */
    @Test
    public void charSourceReadLinesTest() throws Exception {
        List<String> expectedList = Lists.newArrayList("The quick brown", "fox jumps over", "the lazy dog");
        List<String> readList = charSource.readLines();
        assertThat(readList, is(expectedList));
    }

    /**
     * {@link com.google.common.io.CharSource#copyTo(CharSink)}测试
     *
     * @throws Exception
     */
    @Test
    public void copyToCharSinkTest() throws Exception {
        File source = new File("src/test/resources/guava/sampleTextFileTwo.txt");
        File dest = new File("src/test/resources/guava/sampleCopy.txt");
        dest.deleteOnExit();
        charSource = Files.asCharSource(source, Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(dest, Charsets.UTF_8);
        charSource.copyTo(charSink);
        assertThat(Files.toString(dest, Charsets.UTF_8), is(Files.toString(source, Charsets.UTF_8)));
    }
}
