package cn.xyz.chaos.jakarta.common.lang;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.builder.CompareToBuilder}单元测试
 *
 * @author lvchenggang
 *
 */
public class CompareToTest {

    private State state;

    private PoliticalCandidate candidate1;

    private PoliticalCandidate candidate2;

    private PoliticalCandidate candidate3;

    @Before
    public void setUp() {
        state = new State("VA", "Virginia");

        // Create a Birth Date
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 1743);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 13);
        Date dob = calendar.getTime();

        BigDecimal moneyRaised = new BigDecimal(293829292.93);

        // Create a Political Candidate
        candidate1 = new PoliticalCandidate("Jefferson", "Thomas", dob, moneyRaised, state);

        candidate2 = new PoliticalCandidate("Jefferson", "Martha", null, null, null);

        candidate3 = new PoliticalCandidate("Jefferson", "Martha", dob, null, state);

    }

    /**
     * compareTo(),equals()测试
     */
    @Test
    public void testCompareTo() {
        Assert.assertTrue(candidate1.compareTo(candidate2) > 0);
        Assert.assertTrue(candidate2.compareTo(candidate3) == 0);
        Assert.assertTrue(candidate2.equals(candidate3));
    }

}
