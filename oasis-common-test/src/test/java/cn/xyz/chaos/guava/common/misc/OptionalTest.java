package cn.xyz.chaos.guava.common.misc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cn.xyz.chaos.guava.common.model.TradeAccount;

import com.google.common.base.Optional;

/**
 * {@link com.google.common.base.Optional}单元测试
 * <p />
 * Optional可以对对象进行封装,便于我们队Null值对象的判断处理
 *
 * @author lvchenggang
 *
 */
public class OptionalTest {

    /**
     * 对非Null值对象的测试
     */
    @Test
    public void testOptionalNotNull() {
        TradeAccount tradeAccount = new TradeAccount.Builder().build();
        Optional<TradeAccount> tradeAccountOptional = Optional.fromNullable(tradeAccount);
        assertThat(tradeAccountOptional.isPresent(), is(true));
    }

    /**
     * {@link com.google.common.base.Optional#of(Object)}测试
     */
    @Test
    public void testOptionalOfInstance() {
        TradeAccount tradeAccount = new TradeAccount.Builder().build();
        Optional<TradeAccount> tradeAccountOptional = Optional.of(tradeAccount);
        assertThat(tradeAccountOptional.isPresent(), is(true));
    }

    /**
     * 对Null值对象的测试
     */
    @Test(expected = IllegalStateException.class)
    public void testOptionalNull() {
        Optional<TradeAccount> tradeAccountOptional = Optional.fromNullable(null);
        assertThat(tradeAccountOptional.isPresent(), is(false));
        tradeAccountOptional.get();
    }
}
