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
import com.google.common.cache.LoadingCache;

/**
 * {@link com.google.common.cache.LoadingCache}单元测试
 * <p />
 * LoadingCache继承自Cache,并提供缓存自加载功能
 *
 *
 * @author lvchenggang
 *
 */
public class LoadingCacheTest {
    private CacheLoader<String, TradeAccount> cacheLoader = mock(CacheLoader.class);

    /**
     * {@link com.google.common.cache.LoadingCache}缓存不过期测试
     *
     * @throws Exception
     */
    @Test
    public void testCacheLoaderOnlyCalledOnce() throws Exception {
        TradeAccount expectedTradeAccount = new TradeAccount.Builder().balance(250000.12).build();
        when(cacheLoader.load("223")).thenReturn(expectedTradeAccount);
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder().maximumSize(5000L)
                .build(cacheLoader);

        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount, is(expectedTradeAccount));

        for (int i = 0; i < 10; i++) {
            TradeAccount tradeAccount1 = tradeAccountCache.get("223");
            assertThat(tradeAccount1, is(expectedTradeAccount));
        }
        verify(cacheLoader, times(1)).load("223");
    }

    /**
     * {@link com.google.common.cache.LoadingCache}缓存过期测试
     *
     * @throws Exception
     */
    @Test
    public void testCacheLoaderCalledInitiallyAndAfterExpiration() throws Exception {
        TradeAccount expectedTradeAccount = new TradeAccount.Builder().balance(250000.12).build();
        when(cacheLoader.load("223")).thenReturn(expectedTradeAccount);
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder().maximumSize(5000L)
                .expireAfterAccess(500l, TimeUnit.MILLISECONDS).build(cacheLoader);

        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount, is(expectedTradeAccount));

        Thread.sleep(1000);

        TradeAccount tradeAccount1 = tradeAccountCache.get("223");
        assertThat(tradeAccount1, is(expectedTradeAccount));
        verify(cacheLoader, times(2)).load("223");
    }

}
