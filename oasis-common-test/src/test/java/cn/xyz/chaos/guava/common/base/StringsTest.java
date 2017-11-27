package cn.xyz.chaos.guava.common.base;

import org.junit.Test;
import static com.google.common.base.Strings.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * {@link com.google.common.base.Strings}单元测试
 * @author lvchenggang
 *
 */
public class StringsTest {

    /**
     * {@link com.google.common.base.Strings#padEnd(String, int, char)}测试
     */
    @Test
    public void testStringPadEnd() {
        String expected = "boom!!!!!!";
        String returned = padEnd("boom", 10, '!');
        assertThat(returned, is(expected));
    }

    /**
     * {@link com.google.common.base.Strings#padStart(String, int, char)}测试
     */
    @Test
    public void testStringPadStart() {
        String expected = "000000000001";
        String returned = padStart("1", 12, '0');
        assertThat(returned, is(expected));
    }

    /**
     * {@link com.google.common.base.Strings#nullToEmpty(String)}测试
     */
    @Test
    public void testNullToEmpty() {
        assertThat(nullToEmpty("foo"), is("foo"));
        assertThat(nullToEmpty(null), is(""));
    }

    /**
     * {@link com.google.common.base.Strings#emptyToNull(String)}测试
     */
    @Test
    public void tesEmptyToNull() {
        assertThat(nullToEmpty("foo"), is("foo"));
        assertThat(emptyToNull(""), is(nullValue()));
        assertThat(emptyToNull("  "), is("  "));
    }

    /**
     * {@link com.google.common.base.Strings#isNullOrEmpty(String)}测试
     */
    @Test
    public void testIsNullOrEmpty() {
        assertThat(isNullOrEmpty(""), is(true));
        assertThat(isNullOrEmpty("  "), is(false));
        assertThat(isNullOrEmpty(null), is(true));
        assertThat(isNullOrEmpty("foo"), is(false));
    }
}
