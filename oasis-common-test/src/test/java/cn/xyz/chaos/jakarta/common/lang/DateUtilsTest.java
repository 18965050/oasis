package cn.xyz.chaos.jakarta.common.lang;

import static org.hamcrest.CoreMatchers.is;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.time.DateUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class DateUtilsTest {

    private Calendar c;

    FastDateFormat formatter = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getDefault(), Locale.getDefault());

    @Before
    public void setup() {
        c = Calendar.getInstance();
        c.set(2014, 5, 25, 16, 38, 0);
    }

    /**
     * {@link org.apache.commons.lang3.time.DateUtils#round(Calendar, int)}测试
     */
    @Test
    public void testRound() {
        Calendar cYear = DateUtils.round(c, Calendar.YEAR);
        Calendar cMonth = DateUtils.round(c, Calendar.MONTH);
        Calendar cDay = DateUtils.round(c, Calendar.DAY_OF_MONTH);

        String yearExpect = "2014-01-01";
        String monthExpect = "2014-07-01";
        String dayExpect = "2014-06-26";
        Assert.assertThat(formatter.format(cYear), is(yearExpect));
        Assert.assertThat(formatter.format(cMonth), is(monthExpect));
        Assert.assertThat(formatter.format(cDay), is(dayExpect));

    }

    /**
     * {@link org.apache.commons.lang3.time.DateUtils#truncate(Calendar, int)}测试
     */
    @Test
    public void testTruncate() {
        Calendar cYear = DateUtils.truncate(c, Calendar.YEAR);
        Calendar cMonth = DateUtils.truncate(c, Calendar.MONTH);
        Calendar cDay = DateUtils.truncate(c, Calendar.DAY_OF_MONTH);

        String yearExpect = "2014-01-01";
        String monthExpect = "2014-06-01";
        String dayExpect = "2014-06-25";
        Assert.assertThat(formatter.format(cYear), is(yearExpect));
        Assert.assertThat(formatter.format(cMonth), is(monthExpect));
        Assert.assertThat(formatter.format(cDay), is(dayExpect));
    }

}
