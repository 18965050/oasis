package cn.xyz.chaos.jakarta.common.lang;

import static org.hamcrest.CoreMatchers.is;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.StringUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class StringUtilsTest {

    private String s1, s2, s3, s4, s5;

    @Before
    public void setup() {
        s1 = "";
        s2 = "\n\n\t";
        s3 = null;
        s4 = "Test";
        s5 = "This is a test of the abbreviation.";

    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#isBlank(CharSequence)}, <br />
     * {@link org.apache.commons.lang3.StringUtils#isNotBlank(CharSequence)}测试
     */
    @Test
    public void testIsBlank() {
        Assert.assertThat(StringUtils.isBlank(s1), is(true));
        Assert.assertThat(StringUtils.isBlank(s2), is(true));
        Assert.assertThat(StringUtils.isBlank(s3), is(true));
        Assert.assertThat(StringUtils.isBlank(s4), is(false));

        Assert.assertThat(StringUtils.isNotBlank(s1), is(false));
        Assert.assertThat(StringUtils.isNotBlank(s2), is(false));
        Assert.assertThat(StringUtils.isNotBlank(s3), is(false));
        Assert.assertThat(StringUtils.isNotBlank(s4), is(true));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#trimToNull(String)}测试
     */
    @Test
    public void testTrimToNull() {
        Assert.assertThat(StringUtils.trimToNull(s1), is((String) null));
        Assert.assertThat(StringUtils.trimToNull(s2), is((String) null));
        Assert.assertThat(StringUtils.trimToNull(s3), is((String) null));
        Assert.assertThat(StringUtils.trimToNull(s4), is(s4));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#abbreviate(String, int)},<br />
     * {@link org.apache.commons.lang3.StringUtils#abbreviate(String, int, int)} 测试
     */
    @Test
    public void testAbbreviate() {
        Assert.assertThat(StringUtils.abbreviate(s4, 10), is(s4));
        Assert.assertThat(StringUtils.abbreviate(s5, 10), is("This is..."));
        Assert.assertThat(StringUtils.abbreviate(s5, 10, 7), is("...t..."));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#split(String, String, int)}测试
     */
    @Test
    public void testSplit() {
        String input = "Frantically oblong";
        String input2 = "Pharmacy,basketball,funky";
        Assert.assertThat(StringUtils.split(input, " ", 2), is(new String[] { "Frantically", "oblong" }));
        Assert.assertThat(StringUtils.split(input2, ",", 2), is(new String[] { "Pharmacy", "basketball,funky" }));
        Assert.assertThat(StringUtils.split(input2, ",", 3), is(new String[] { "Pharmacy", "basketball", "funky" }));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#substringBetween(String, String, String)} 测试
     */
    @Test
    public void testSubstringBetween() {
        String htmlContent = "<html>\n" + "  <head>\n" + "    <title>Test Page</title>\n" + "  </head>\n" + "  <body>\n"
                + "    <p>This is a TEST!</p>\n" + "  </body>\n" + "</html>";
        Assert.assertThat(StringUtils.substringBetween(htmlContent, "<p>", "</p>"), is("This is a TEST!"));

    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#trim(String)}测试
     */
    @Test
    public void testTrim() {
        String test1 = " \r\n Testing 1 2 3 ";
        String test2 = " \r\n ";
        Assert.assertThat(StringUtils.trim(test1), is("Testing 1 2 3"));
        Assert.assertThat(StringUtils.trimToNull(test2), is((String) null));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#strip(String, String)}测试
     */
    @Test
    public void testStrip() {
        String test = "-------***---SHAZAM!---***-------";
        Assert.assertThat(StringUtils.strip(test, "*-"), is("SHAZAM!"));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#chomp(String)}测试
     */
    @Test
    public void testChomp() {
        String input = "Hello\n";
        String input2 = "Another test\r\n";

        Assert.assertThat(StringUtils.chomp(input), is("Hello"));
        Assert.assertThat(StringUtils.chomp(input2), is("Another test"));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#repeat(char, int)},<br />
     * {@link org.apache.commons.lang3.StringUtils#center(String, int, char)},<br />
     * {@link org.apache.commons.lang3.StringUtils#join(Object...)}测试
     */
    @Test
    public void testEmphasize() {
        String title = "important";
        String stars = StringUtils.repeat("*", 20);
        String centered = StringUtils.center(title, 20, "*");
        String res = StringUtils.join(new String[] { stars, centered, stars }, "\r\n");
        Assert.assertThat(res, is("********************\r\n*****important******\r\n********************"));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#isNumeric(CharSequence)}, <br />
     * {@link org.apache.commons.lang3.StringUtils#isAlpha(CharSequence)}, <br />
     * {@link org.apache.commons.lang3.StringUtils#isAlphanumeric(CharSequence)} , <br />
     * {@link org.apache.commons.lang3.StringUtils#isAlphaSpace(CharSequence)}测试
     */
    @Test
    public void testContent() {
        String state = "Virginia123";
        Assert.assertFalse(StringUtils.isNumeric(state));
        Assert.assertFalse(StringUtils.isAlpha(state));
        Assert.assertTrue(StringUtils.isAlphanumeric(state));
        Assert.assertFalse(StringUtils.isAlphaSpace(state));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#substringBefore(String, String)} , <br />
     * {@link org.apache.commons.lang3.StringUtils#substringBetween(String, String, String)} , <br />
     * {@link org.apache.commons.lang3.StringUtils#substringAfterLast(String, String)} 测试
     */
    @Test
    public void testFormat() {
        String formatted = "25* (30,40) [50;60] |30";
        Assert.assertThat(StringUtils.substringBeforeLast(formatted, "*"), is("25"));
        Assert.assertThat(StringUtils.substringBetween(formatted, "(", ","), is("30"));
        Assert.assertThat(StringUtils.substringBetween(formatted, ",", ")"), is("40"));
        Assert.assertThat(StringUtils.substringBetween(formatted, "[", ";"), is("50"));
        Assert.assertThat(StringUtils.substringBetween(formatted, ";", "]"), is("60"));
        Assert.assertThat(StringUtils.substringAfterLast(formatted, "|"), is("30"));
    }

    /**
     * {@link org.apache.commons.lang3.StringUtils#getLevenshteinDistance(CharSequence, CharSequence)} , <br />
     * {@link org.apache.commons.lang3.StringUtils#difference(String, String)},<br />
     * {@link org.apache.commons.lang3.StringUtils#indexOfDifference(CharSequence, CharSequence)} 测试
     */
    @Test
    public void testDifference() {
        String s1 = "word";
        String s2 = "World";
        Assert.assertThat(StringUtils.getLevenshteinDistance(s1, s2), is(2));
        Assert.assertThat(StringUtils.difference(s1, s2), is("World"));
        Assert.assertThat(StringUtils.indexOfDifference(s1, s2), is(0));
    }
}
