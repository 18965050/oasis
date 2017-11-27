package cn.xyz.chaos.guava.common.base;

import com.google.common.base.CharMatcher;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * {@link com.google.common.base.CharMatcher}单元测试
 * @author lvchenggang
 *
 */
public class CharMatcherTest {

    /**
     * {@link com.google.common.base.CharMatcher#replaceFrom(CharSequence, char)}测试
     */
    @Test
    public void testRemoveLinebreaks() {
        String stringWithLinebreaks = "This is an example\n" + "of a String with linebreaks\n" + "we want on one line";
        String expected = "This is an example of a String with linebreaks we want on one line";
        String scrubbed = CharMatcher.BREAKING_WHITESPACE.replaceFrom(stringWithLinebreaks, ' ');
        assertThat(scrubbed, is(expected));
    }

    /**
     * {@link com.google.common.base.CharMatcher#collapseFrom(CharSequence, char)}测试
     */
    @Test
    public void testRemoveWhiteSpace() {
        String tabsAndSpaces = "String  with      spaces     and           tabs";
        String expected = "String with spaces and tabs";
        String scrubbed = CharMatcher.WHITESPACE.collapseFrom(tabsAndSpaces, ' ');
        assertThat(scrubbed, is(expected));
    }

    /**
     * {@link com.google.common.base.CharMatcher#trimAndCollapseFrom(CharSequence, char)}测试
     */
    @Test
    public void testTrimRemoveWhiteSpace() {
        String tabsAndSpaces = "    String  with      spaces     and           tabs";
        String expected = "String with spaces and tabs";
        String scrubbed = CharMatcher.WHITESPACE.trimAndCollapseFrom(tabsAndSpaces, ' ');
        assertThat(scrubbed, is(expected));
    }

    /**
     * {@link com.google.common.base.CharMatcher#retainFrom(CharSequence)}测试
     */
    @Test
    public void testRetainFrom() {
        String lettersAndNumbers = "foo989yxbar234";
        String expected = "989234";
        String retained = CharMatcher.JAVA_DIGIT.retainFrom(lettersAndNumbers);
        assertThat(expected, is(retained));
    }

    /**
     * {@link com.google.common.base.CharMatcher#inRange(char, char)}测试
     */
    @Test
    public void testCombineMatchers() {
        CharMatcher cm = CharMatcher.inRange('A', 'E');
        assertThat(cm.retainFrom("aaaABbbccCdddDEeee"), is("ABCDE"));
    }
}
