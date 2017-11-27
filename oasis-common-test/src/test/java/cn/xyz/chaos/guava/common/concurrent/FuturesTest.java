package cn.xyz.chaos.guava.common.concurrent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * {@link com.google.common.util.concurrent.Futures}单元测试
 *
 * @author lvchenggang
 *
 */
public class FuturesTest {

    private int numberTasks;
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;
    private ListeningExecutorService executorService;

    @Before
    public void setUp() throws Exception {
        numberTasks = 5;
        startSignal = new CountDownLatch(1);
        doneSignal = new CountDownLatch(numberTasks);
        executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
    }

    @After
    public void tearDown() {
        executorService.shutdownNow();
    }

    /**
     * {@link com.google.common.util.concurrent.Futures#withFallback(ListenableFuture, com.google.common.util.concurrent.FutureFallback)}
     * 测试
     *
     * @throws Exception
     */
    @Test
    public void testWithFutureFallbackFailed() throws Exception {
        ListenableFuture<String> readFileAsString = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Files.toString(new File("bogus"), Charsets.UTF_8);
            }
        });
        ListenableFuture<String> withFallbackFutureFailed = Futures.withFallback(readFileAsString,
                new FutureFallbackImpl());
        assertThat(withFallbackFutureFailed.get(), is("Not Found"));
    }

}
