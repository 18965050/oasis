package cn.xyz.chaos.jakarta.common.lang;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.builder.ReflectionToStringBuilder}, <br />
 * {@link org.apache.commons.lang3.builder.ToStringBuilder}单元测试
 *
 * @author lvchenggang
 *
 */
public class ToStringTest {

    private State state;

    private PoliticalCandidate candidate;

    @Before
    public void setUp() {
        // Create a State
        state = new State("VA", "Virginia");

        // Create a Birth Date
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 1743);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        Date dob = calendar.getTime();

        BigDecimal moneyRaised = new BigDecimal(293829292.93);

        // Create a Political Candidate
        candidate = new PoliticalCandidate("Jefferson", "Thomas", dob, moneyRaised, state);
    }

    /**
     * toString()测试
     */
    @Test
    public void testToString() {
        String stateExpect = "cn.xyz.chaos.jakarta.common.lang.State@.*\\[abbreviation=VA,name=Virginia\\]";
        Pattern p = Pattern.compile(stateExpect);
        Matcher m = p.matcher(state.toString());
        Assert.assertTrue(m.find());
        String candidateExpect = "Jefferson,Thomas";
        Assert.assertEquals(candidate.toString(), candidateExpect);
    }
}
