package cn.xyz.chaos.guava.common.file;

/**
 * User: Bill Bejeck
 * Date: 5/2/13
 * Time: 11:22 PM
 */

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

/**
 * {@link com.google.common.io.Files#readLines}单元测试
 *
 * @author lvchenggang
 *
 */
public class ReadLinesTest {

    /**
     * {@link com.google.common.io.Files#readLines(File, java.nio.charset.Charset)} 测试
     *
     * @throws Exception
     */
    @Test
    public void readFileIntoListOfStringsTest() throws Exception {
        File file = new File("src/test/resources/guava/lines.txt");
        List<String> expectedLines = Lists.newArrayList("The quick brown", "fox jumps over", "the lazy dog");
        List<String> readLines = Files.readLines(file, Charsets.UTF_8);
        assertThat(expectedLines, is(readLines));
    }

    /**
     * {@link com.google.common.io.Files#readLines(File, java.nio.charset.Charset, com.google.common.io.LineProcessor)}
     * 测试
     *
     * @throws Exception
     */
    @Test
    public void readLinesWithProcessor() throws Exception {
        File file = new File("src/test/resources/guava/books.data");
        List<String> expectedLines = Lists.newArrayList("Being A Great Cook", "Art is Fun", "Be an Architect",
                "History of Football", "Gardening My Way", "How I Made Millions");
        List<String> readLines = Files.readLines(file, Charsets.UTF_8, new ToListLineProcessor());
        assertThat(expectedLines, is(readLines));
    }

}
