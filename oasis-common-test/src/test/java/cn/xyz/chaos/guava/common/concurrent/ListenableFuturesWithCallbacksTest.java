package cn.xyz.chaos.guava.common.concurrent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * {@link com.google.common.util.concurrent.ListenableFuture}单元测试
 * <p />
 * ListenableFuture是对Future的扩展,支持回调函数.这样,可以避免 {@link java.util.concurrent.Future#get()}方法阻塞
 *
 * @author lvchenggang
 *
 */
public class ListenableFuturesWithCallbacksTest {

    private ListeningExecutorService executorService;
    private CountDownLatch startSignal;
    private CountDownLatch endSignal;
    private static final int NUM_THREADS = 5;
    private boolean callbackRan;

    @Before
    public void setUp() {
        executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(NUM_THREADS));
        startSignal = new CountDownLatch(1);
        endSignal = new CountDownLatch(1);
        callbackRan = false;

    }

    @After
    public void tearDown() {
        executorService.shutdownNow();
    }

    /**
     * {@link com.google.common.util.concurrent.ListenableFuture}添加回调函数测试
     *
     * @throws Exception
     */
    @Test
    public void testRunListenableFutureWithCallback() throws Exception {
        ListenableFuture<String> futureTask = executorService.submit(new Task(startSignal));
        futureTask.addListener(new Runnable() {
            @Override
            public void run() {
                callbackRan = true;
                endSignal.countDown();
            }
        }, executorService);

        endSignal.await();
        assertThat(callbackRan, is(true));
        assertThat(futureTask.get(), is("Task Done"));
    }

    /**
     * {@link com.google.common.util.concurrent.Futures#addCallback(ListenableFuture, FutureCallback)}
     * 给ListenableFuture添加回调函数测试
     *
     * @throws Exception
     */
    @Test
    public void testRunListenableFutureWithFutureCallbackSuccess() throws Exception {
        ListenableFuture<String> futureTask = executorService.submit(new Task(startSignal));
        FutureCallbackImpl callback = new FutureCallbackImpl();
        Futures.addCallback(futureTask, callback);
        startSignal.countDown();
        endSignal.await();
        assertThat(callback.getCallbackResult(), is("Task Done successfully"));
    }

    /**
     * {@link com.google.common.util.concurrent.ListenableFuture}异常测试
     *
     * @throws Exception
     */
    @Test
    public void testRunListenableFutureWithFutureCallbackFailure() throws Exception {
        ListenableFuture<String> futureTask = executorService.submit(new Task(null));
        FutureCallbackImpl callback = new FutureCallbackImpl();
        Futures.addCallback(futureTask, callback);
        // startSignal.countDown(); don't call countdown
        endSignal.await();
        assertThat(callback.getCallbackResult(), is("java.lang.NullPointerException"));
    }

    public class FutureCallbackImpl implements FutureCallback<String> {

        private StringBuilder builder = new StringBuilder();

        @Override
        public void onSuccess(String result) {
            builder.append(result).append(" successfully");
            done();

        }

        @Override
        public void onFailure(Throwable t) {
            builder.append(t.toString());
            done();
        }

        private void done() {
            endSignal.countDown();
        }

        public String getCallbackResult() {
            return builder.toString();
        }
    }

    private class Task implements Callable<String> {
        private CountDownLatch start;

        public Task(CountDownLatch start) {
            this.start = start;
        }

        @Override
        public String call() throws Exception {
            this.start.await(1, TimeUnit.SECONDS);
            Thread.sleep(1000);
            return "Task Done";
        }
    }

}
