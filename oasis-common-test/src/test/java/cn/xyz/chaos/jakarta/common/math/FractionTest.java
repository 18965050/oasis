package cn.xyz.chaos.jakarta.common.math;

import static org.hamcrest.CoreMatchers.is;

import java.text.NumberFormat;

import org.apache.commons.lang3.math.Fraction;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.lang3.math.Fraction}单元测试
 *
 * @author lvchenggang
 *
 */
public class FractionTest {

    @Test
    public void testFraction() {
        String userInput = "23 31/37";
        Fraction fraction = Fraction.getFraction(userInput);
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        Assert.assertThat(nf.format(fraction.doubleValue()), is("23.84"));

        /** (1/2)*(100/50) */
        Fraction numer1 = Fraction.getFraction(1, 2);
        Fraction numer2 = Fraction.getFraction(100, 50);
        Fraction numerator = numer1.multiplyBy(numer2);
        Assert.assertThat(numerator.intValue(), is(1));
    }

}
