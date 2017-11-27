package cn.xyz.chaos.guava.common.cache;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import cn.xyz.chaos.guava.common.model.TradeAccount;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

/**
 * {@link com.google.common.cache.CacheStats}单元测试
 * <p />
 * CacheStats用于Cache操作行为记录.CacheStats对性能有影响, 使用时需要注意
 *
 * @author lvchenggang
 *
 */
public class CacheStatsTest {
    private CacheLoader<String, TradeAccount> cacheLoader = mock(CacheLoader.class);

    @Test
    public void testGetCacheStats() throws Exception {
        TradeAccount expectedTradeAccount = new TradeAccount.Builder().build();
        when(cacheLoader.load("223")).thenReturn(expectedTradeAccount);
        when(cacheLoader.load("000")).thenThrow(new RuntimeException("Bad Value"));
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder().maximumSize(5000L)
                .expireAfterAccess(10l, TimeUnit.MILLISECONDS).recordStats().build(cacheLoader);

        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount, is(expectedTradeAccount));
        TradeAccount tradeAccount1 = tradeAccountCache.get("223");
        assertThat(tradeAccount1, is(expectedTradeAccount));
        Thread.sleep(20);
        TradeAccount tradeAccount2 = tradeAccountCache.get("223");
        assertThat(tradeAccount2, is(expectedTradeAccount));
        try {
            tradeAccountCache.get("000");
        } catch (RuntimeException e) {
            // Ignore expected
        }
        CacheStats stats = tradeAccountCache.stats();

        assertThat(stats.evictionCount(), is(1l));
        assertThat(stats.hitCount(), is(1l));
        assertThat(stats.missCount(), is(3l));
        assertThat(stats.hitRate(), is((double) 1 / 4));
        assertThat(stats.loadExceptionCount(), is(1l));
        assertThat(stats.loadExceptionRate(), is((double) 1 / 3));
        assertThat(stats.loadSuccessCount(), is(2l));

        verify(cacheLoader, times(2)).load("223");
    }
}
