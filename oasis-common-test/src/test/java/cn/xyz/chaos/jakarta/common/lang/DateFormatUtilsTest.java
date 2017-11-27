package cn.xyz.chaos.jakarta.common.lang;

import static org.hamcrest.CoreMatchers.is;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.time.DateFormatUtils}单元测试
 *
 * @author lvchenggang
 *
 */
public class DateFormatUtilsTest {

    /**
     * {@link org.apache.commons.lang3.time.DateFormatUtils},<br />
     * {@link org.apache.commons.lang3.time.FastDateFormat}测试
     */
    @Test
    public void testDateFormat() {
        Calendar c = Calendar.getInstance();
        c.set(2014, 5, 25, 16, 38, 0);
        String isoDT = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(c);
        Assert.assertThat(isoDT, is("2014-06-25T16:38:00+08:00"));

        FastDateFormat formatter = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss", TimeZone.getDefault(),
                Locale.getDefault());
        Assert.assertThat(formatter.format(c), is("2014-06-25 16:38:00"));

    }
}
