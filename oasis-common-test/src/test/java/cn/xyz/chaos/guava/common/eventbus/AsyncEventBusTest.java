package cn.xyz.chaos.guava.common.eventbus;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import org.junit.Before;
import org.junit.Test;

import cn.xyz.chaos.guava.common.eventbus.subscriber.SlowProcessSubscriber;

import com.google.common.eventbus.AsyncEventBus;

/**
 * {@link com.google.common.eventbus.AsyncEventBus}单元测试
 * <p />
 * EventBus中对于同一事件的所有Subscriber,其事件触发是串行执行的,这不太适用于耗时的操作, <br />
 * 在这种情况下, 可以考虑配合适用AsyncEventBus+@AllowConcurrentEvents的方式进行多线程操作
 *
 * @author lvchenggang
 *
 */
public class AsyncEventBusTest extends EventBusTestBase {
    private AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());
    private int numberLongEvents = 10;
    private CountDownLatch doneSignal = new CountDownLatch(numberLongEvents);
    private SlowProcessSubscriber slowProcessSubscriber;

    @Before
    public void setUp() {
        slowProcessSubscriber = new SlowProcessSubscriber(asyncEventBus, doneSignal);
    }

    /**
     * AsyncEventBus配合AllowConcurrentEvents注解的方式测试,这种情况下每个订阅者在不同线程触发
     *
     * @throws Exception
     */
    @Test
    public void testAsyncEventSubscriber() throws Exception {

        long start = System.currentTimeMillis();
        for (int i = 0; i < numberLongEvents; i++) {
            asyncEventBus.post(buyEventBuilder().build());
        }
        doneSignal.await();
        long elapsed = System.currentTimeMillis() - start;
        /** Maven单元测试时,考虑到Maven运行会占用一定时间, 因此设置为1000. 在Eclipse中设置为500即可 */
        assertTrue(elapsed >= 200l && elapsed < 1000l);
    }

    /**
     * 只使用AsyncEventBus的方式. 这种情况下每个订阅者还是串行触发
     *
     * @throws Exception
     */
    @Test
    public void testNonAsyncEventSubscriber() throws Exception {

        long start = System.currentTimeMillis();
        for (int i = 0; i < numberLongEvents; i++) {
            asyncEventBus.post(sellEventBuilder().build());
        }
        doneSignal.await();
        long elapsed = System.currentTimeMillis() - start;
        /** Maven单元测试时,考虑到Maven运行会占用一定时间, 因此设置为10000. 在Eclipse中设置为5000即可 */
        assertTrue(elapsed >= 2000l && elapsed < 10000l);
    }
}
