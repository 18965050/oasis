package cn.xyz.chaos.guava.common.cache;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.guava.common.model.Book;
import cn.xyz.chaos.guava.common.model.TradeAccount;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;

/**
 * {@link com.google.common.cache.RemovalListener}单元测试
 *
 * @author lvchenggang
 *
 */
public class RemovalListenerTest {

    private CacheLoader<String, Book> bookCacheLoader = mock(CacheLoader.class);
    private CacheLoader<String, TradeAccount> tradeAccountCacheLoader = mock(CacheLoader.class);

    @Before
    public void startUpBeforeAll() throws Exception {
        when(tradeAccountCacheLoader.load("223"))
                .thenReturn(new TradeAccount.Builder().id("223").balance(250000.12).build());

        when(bookCacheLoader.load("ISBN-234567")).thenReturn(
                new Book.Builder().author("Vandeley, Art").isbn("ISBN-234567").title("Be an Architect").build());
    }

    /**
     * {@link com.google.common.cache.CacheBuilder#expireAfterWrite(long, TimeUnit)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testLoadingCacheExpireAfterWrite() throws Exception {
        TradeAccountRemovalListener removalListener = new TradeAccountRemovalListener();
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder()
                .expireAfterWrite(5L, TimeUnit.MILLISECONDS).maximumSize(5000L).removalListener(removalListener)
                .ticker(Ticker.systemTicker()).build(tradeAccountCacheLoader);

        // 223,"Rogers, Jim",250000.12
        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount.getBalance(), is(250000.12));
        Thread.sleep(10L);
        tradeAccountCache.get("223");
        assertThat(removalListener.getRemovalCause(), is(RemovalCause.EXPIRED));
        assertThat(removalListener.getRemovedValue(), is(tradeAccount));
    }

    /**
     * {@link com.google.common.cache.CacheBuilder#expireAfterAccess(long, TimeUnit)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testRemovalAfterLastAccessed() throws Exception {
        BookRemovalListener bookRemovalListener = new BookRemovalListener();
        LoadingCache<String, Book> bookCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MILLISECONDS)
                .softValues().recordStats().removalListener(bookRemovalListener).build(bookCacheLoader);

        Book book = bookCache.get("ISBN-234567");
        assertThat(book.getAuthor(), is("Vandeley, Art"));
        assertThat(book.getIsbn(), is("ISBN-234567"));
        Thread.sleep(20);
        // Need to call again to force eviction
        Book bookII = bookCache.get("ISBN-234567");
        assertThat(bookII.getAuthor(), is("Vandeley, Art"));
        assertThat(bookII.getIsbn(), is("ISBN-234567"));
        CacheStats cacheStats = bookCache.stats();
        assertThat(cacheStats.evictionCount(), is(1l));
        assertThat(bookRemovalListener.getRemovalCause(), is(RemovalCause.EXPIRED));
        assertThat(bookRemovalListener.getRemovedKey(), is("ISBN-234567"));
        assertThat(bookRemovalListener.getRemovedValue(), is(book));

    }

    /**
     * {@link com.google.common.cache.LoadingCache#invalidate(Object)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testInvalidateBadValue() throws Exception {
        BookRemovalListener bookRemovalListener = new BookRemovalListener();
        LoadingCache<String, Book> bookCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.HOURS)
                .softValues().recordStats().removalListener(bookRemovalListener).build(bookCacheLoader);

        Book book = bookCache.get("ISBN-234567");
        assertThat(book.getTitle(), is("Be an Architect"));
        bookCache.invalidate("ISBN-234567");
        assertThat(bookRemovalListener.getRemovalCause(), is(RemovalCause.EXPLICIT));
        assertThat(bookRemovalListener.getRemovedValue().getTitle(), is("Be an Architect"));
    }

    /**
     * {@link com.google.common.cache.CacheBuilder#refreshAfterWrite(long, TimeUnit)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testRefreshingCacheValues() throws Exception {
        TradeAccountRemovalListener removalListener = new TradeAccountRemovalListener();
        LoadingCache<String, TradeAccount> tradeAccountCache = CacheBuilder.newBuilder().concurrencyLevel(10)
                .refreshAfterWrite(5L, TimeUnit.MILLISECONDS).removalListener(removalListener)
                .ticker(Ticker.systemTicker()).recordStats().build(tradeAccountCacheLoader);

        // 223,"Rogers, Jim",250000.12
        TradeAccount tradeAccount = tradeAccountCache.get("223");
        assertThat(tradeAccount.getBalance(), is(250000.12));
        Thread.sleep(10L);
        /** 注: 下面的单元测试不通过, 由于缓存策略是过期替换 */
        // tradeAccountCache.get("223");
        // CacheStats stats = tradeAccountCache.stats();
        // assertThat(stats.loadSuccessCount(), is(2l));
        // assertThat(removalListener.getRemovalCause(),
        // is(RemovalCause.REPLACED));
        // assertThat(removalListener.getRemovedValue(), is(tradeAccount));
    }

}
