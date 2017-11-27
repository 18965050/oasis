package cn.xyz.chaos.guava.common.cache;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import cn.xyz.chaos.guava.common.model.TradeAccount;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;

/**
 * {@link com.google.common.cache.CacheBuilderSpec}单元测试
 * <p />
 * CacheBuilderSpec是CacheBuilder的辅助类, 用于Cache参数设置
 *
 * @author lvchenggang
 *
 */
public class CacheBuilderSpecTest {

    private CacheLoader<String, TradeAccount> cacheLoader = mock(CacheLoader.class);

    @Test
    public void testCacheBuilderSpec() throws Exception {

        when(cacheLoader.load("112"))
                .thenReturn(new TradeAccount.Builder().id("112").owner("Pennypacker, HJ").balance(700889.32).build());

        TradeAccountRemovalListener removalListener = new TradeAccountRemovalListener();
        String spec = "concurrencyLevel=10,expireAfterAccess=1s,softValues";
        CacheBuilderSpec cacheBuilderSpec = CacheBuilderSpec.parse(spec);
        CacheBuilder cacheBuilder = CacheBuilder.from(cacheBuilderSpec);
        LoadingCache<String, TradeAccount> tradeAccountCache = cacheBuilder.ticker(Ticker.systemTicker())
                .removalListener(removalListener).build(cacheLoader);

        // 112,"Pennypacker, HJ",700889.32
        TradeAccount tradeAccount = tradeAccountCache.get("112");
        assertThat(tradeAccount.getBalance(), is(700889.32));
        Thread.sleep(1500L);
        tradeAccountCache.get("112");
        assertThat(removalListener.getRemovalCause(), is(RemovalCause.EXPIRED));
        assertThat(removalListener.getRemovedValue(), is(tradeAccount));

    }
}
