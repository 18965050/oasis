package cn.xyz.chaos.guava.common.concurrent;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * {@link com.google.common.util.concurrent.Monitor}单元测试
 * <p />
 * Monitor是一个线程同步控制器
 *
 * @author lvchenggang
 *
 */
public class MonitorExampleTest {

    private MonitorExample monitorExample;
    private ExecutorService executorService;
    private int numberThreads = 10;
    private CountDownLatch startSignal;
    private CountDownLatch doneSignal;

    @Before
    public void setUp() throws Exception {
        monitorExample = new MonitorExample();
        executorService = Executors.newFixedThreadPool(numberThreads);
        startSignal = new CountDownLatch(1);
        doneSignal = new CountDownLatch(numberThreads);
    }

    @After
    public void tearDown() {
        executorService.shutdownNow();
    }

    /**
     * {@link com.google.common.util.concurrent.Monitor#tryEnterIf(com.google.common.util.concurrent.Monitor.Guard)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testDemoTryEnterIf() throws Exception {
        setUpThreadsForTestingMethod("demoTryEnterIf");
        startAllThreadsForTest();
        waitForTestThreadsToFinish();
        int expectedTaskCount = 1;
        int expectedSkippedTasks = 9;
        assertThat(monitorExample.getTaskDoneCounter(), is(expectedTaskCount));
        assertThat(monitorExample.getTaskSkippedCounter(), is(expectedSkippedTasks));
    }

    /**
     * {@link com.google.common.util.concurrent.Monitor#enterIf(com.google.common.util.concurrent.Monitor.Guard)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testDemoEnterIfOnlyFiveTasksComplete() throws Exception {
        monitorExample.setStopTaskCount(5);
        setUpThreadsForTestingMethod("demoEnterIf");

        startAllThreadsForTest();
        waitForTestThreadsToFinish();
        int expectedTaskCount = 5;
        int expectedSkippedTasks = 5;

        assertThat(monitorExample.getTaskDoneCounter(), is(expectedTaskCount));
        assertThat(monitorExample.getTaskSkippedCounter(), is(expectedSkippedTasks));

    }

    /**
     * {@link com.google.common.util.concurrent.Monitor#enterIf(com.google.common.util.concurrent.Monitor.Guard)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testDemoEnterIfAllTasksComplete() throws Exception {
        monitorExample.setStopTaskCount(Integer.MAX_VALUE);
        setUpThreadsForTestingMethod("demoEnterIf");

        startAllThreadsForTest();
        waitForTestThreadsToFinish();
        int expectedTaskCount = 10;
        int expectedSkippedTasks = 0;

        assertThat(monitorExample.getTaskDoneCounter(), is(expectedTaskCount));
        assertThat(monitorExample.getTaskSkippedCounter(), is(expectedSkippedTasks));

    }

    /**
     * {@link com.google.common.util.concurrent.Monitor#enterWhen(com.google.common.util.concurrent.Monitor.Guard)} 测试
     *
     * @throws Exception
     */
    @Test
    public void testDemoEnterWhen() throws Exception {
        monitorExample.setStopTaskCount(Integer.MAX_VALUE);
        monitorExample.setCondition(false);
        setUpThreadsForTestingMethod("demoEnterWhen");
        startAllThreadsForTest();
        int expectedCompletedCount = 0;
        int completedCount = monitorExample.getTaskDoneCounter();
        assertThat(completedCount, is(expectedCompletedCount));

        monitorExample.setCondition(true);

        waitForTestThreadsToFinish();
        expectedCompletedCount = 10;
        completedCount = monitorExample.getTaskDoneCounter();
        assertThat(completedCount, is(expectedCompletedCount));
    }

    private void waitForTestThreadsToFinish() throws InterruptedException {
        doneSignal.await(1000l, TimeUnit.MILLISECONDS);
    }

    private void startAllThreadsForTest() {
        startSignal.countDown();
    }

    private Method getMethodUnderTest(String methodName) throws Exception {
        return monitorExample.getClass().getDeclaredMethod(methodName);
    }

    private void setUpThreadsForTestingMethod(String methodName) throws Exception {
        final Method testMethod = getMethodUnderTest(methodName);
        for (int i = 0; i < numberThreads; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        startSignal.await();
                        testMethod.invoke(monitorExample);
                    } catch (Exception e) {
                        // Don't care
                    } finally {
                        doneSignal.countDown();
                    }
                }
            });
        }
    }

}
