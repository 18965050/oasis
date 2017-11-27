package cn.xyz.chaos.guava.common.base;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * {@link com.google.common.base.Splitter}单元测试
 * @author lvchenggang
 *
 */
public class SplitterTest {

    /**
     * {@link com.google.common.base.Splitter.MapSplitter}测试
     */
    @Test
    public void testSplitter() {
        String startSring = "Washington D.C=Redskins#New York City=Giants#Philadelphia=Eagles#Dallas=Cowboys";
        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Washington D.C", "Redskins");
        testMap.put("New York City", "Giants");
        testMap.put("Philadelphia", "Eagles");
        testMap.put("Dallas", "Cowboys");
        Splitter.MapSplitter mapSplitter = Splitter.on("#").withKeyValueSeparator("=");
        Map<String, String> splitMap = mapSplitter.split(startSring);
        assertThat(testMap, is(splitMap));
    }

    /**
     * {@link com.google.common.base.Splitter}正则Pattern匹配测试
     */
    @Test
    public void testSplitPattern() {
        String pattern = "\\d+";
        String text = "foo123bar45678baz";
        String[] expected = new String[] { "foo", "bar", "baz" };
        Iterable<String> values = Splitter.on(Pattern.compile(pattern)).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter}正则String匹配测试
     */
    @Test
    public void testSplitStringPattern() {
        String pattern = "\\d+";
        String text = "foo123bar45678baz";
        String[] expected = new String[] { "foo", "bar", "baz" };
        Iterable<String> values = Splitter.onPattern(pattern).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter#split(CharSequence)}测试
     */
    @Test
    public void testSplit() {
        String delimiter = "&";
        String text = "foo&bar&baz";
        String[] expected = new String[] { "foo", "bar", "baz" };
        Iterable<String> values = Splitter.on(delimiter).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter#trimResults()}测试 <br/>
     * 注意:请采用fluent style,否则会生成一个新的Splitter对象. 这样{@code trimResults}就不起作用了
     */
    @Test
    public void testSplitTrimResults() {
        String delimiter = "&";
        String text = "foo   &  bar&   baz  ";
        String[] expected = new String[] { "foo", "bar", "baz" };
        // Splitter splitter= Splitter.on(delimiter);
        // splitter.trimResults();
        // Iterable<String> values = splitter.split(text);

        Iterable<String> values = Splitter.on(delimiter).trimResults().split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter#trimResults(CharMatcher)}测试 <br/>
     * 注意:请采用fluent style,否则会生成一个新的Splitter对象. 这样{@code trimResults}就不起作用了
     */
    @Test
    public void testSplitTrimResultsII() {
        String delimiter = "&";
        String text = "1foo&bar2&2baz3";
        String[] expected = new String[] { "foo", "bar", "baz" };
        Iterable<String> values = Splitter.on(delimiter).trimResults(CharMatcher.JAVA_DIGIT).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter#split(CharSequence)}测试 <br/>
     */
    @Test
    public void testSplitOnCharacter() {
        char delimiter = '|';
        String text = "foo|bar|baz";
        String[] expected = new String[] { "foo", "bar", "baz" };
        Iterable<String> values = Splitter.on(delimiter).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter}保留空值对象测试 <br/>
     */
    @Test
    public void testSplitOnCharacterKeepMissing() {
        char delimiter = '|';
        String text = "foo|bar|||baz";
        String[] expected = new String[] { "foo", "bar", "", "", "baz" };
        Iterable<String> values = Splitter.on(delimiter).split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }

    /**
     * {@link com.google.common.base.Splitter}去除空值对象测试 <br/>
     */
    @Test
    public void testSplitOnCharacterOmitMissing() {
        char delimiter = '|';
        String text = "foo|bar|||baz";
        String[] expected = new String[] { "foo", "bar", "baz" };
        Iterable<String> values = Splitter.on(delimiter).omitEmptyStrings().split(text);
        int index = 0;
        for (String value : values) {
            assertThat(value, is(expected[index++]));
        }
    }
}
