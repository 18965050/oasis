package cn.xyz.chaos.guava.common.file;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.google.common.io.OutputSupplier;

/**
 * {@link com.google.common.io.CharStreams}单元测试
 * <p />
 * CharStreams是Reader和Writer的工具类
 *
 * @author lvchenggang
 *
 */
public class CharStreamsTest {

    /**
     * {@link com.google.common.io.CharStreams#join(Iterable)}测试
     *
     * @throws Exception
     */
    @Test
    public void joinTest() throws Exception {
        /*
         * File f1 = new File("src/test/resources/guava/sampleTextFileOne.txt"); File f2 = new
         * File("src/test/resources/guava/sampleTextFileTwo.txt"); File f3 = new
         * File("src/test/resources/guava/lines.txt"); File joinedOutput = new
         * File("src/test/resources/guava/joined.txt"); joinedOutput.deleteOnExit();
         * List<InputSupplier<InputStreamReader>> inputSuppliers = getInputSuppliers( f1, f2, f3); InputSupplier<Reader>
         * joinedSupplier = CharStreams.join(inputSuppliers); OutputSupplier<OutputStreamWriter> outputSupplier = Files
         * .newWriterSupplier(joinedOutput, Charsets.UTF_8); String expectedOutputString = joinFiles(f1, f2, f3);
         * CharStreams.copy(joinedSupplier, outputSupplier); String joinedOutputString = joinFiles(joinedOutput);
         * assertThat(joinedOutputString, is(expectedOutputString));
         */
    }

    private String joinFiles(File... files) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (File file : files) {
            builder.append(Files.toString(file, Charsets.UTF_8));
        }
        return builder.toString();
    }

    private List<InputSupplier<InputStreamReader>> getInputSuppliers(File... files) {
        List<InputSupplier<InputStreamReader>> list = Lists.newArrayList();
        /*
         * for (File file : files) { list.add(Files.newReaderSupplier(file, Charsets.UTF_8)); }
         */
        return list;
    }

}
