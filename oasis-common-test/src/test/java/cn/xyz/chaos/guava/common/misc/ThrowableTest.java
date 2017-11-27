package cn.xyz.chaos.guava.common.misc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.google.common.base.Throwables;

/**
 * {@link com.google.common.base.Throwables}单元测试
 * <p />
 * Throwables用于对Throwable对象进行处理
 *
 * @author lvchenggang
 *
 */
public class ThrowableTest {

    /**
     * {@link com.google.common.base.Throwables#getCausalChain(Throwable)}测试
     */
    @Test
    public void testGetCausalChain() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        List<Throwable> throwables = null;
        Callable<FileInputStream> fileCallable = new Callable<FileInputStream>() {
            @Override
            public FileInputStream call() throws Exception {
                return new FileInputStream("Bogus file");
            }
        };
        Future<FileInputStream> fisFuture = executor.submit(fileCallable);
        try {
            fisFuture.get();
        } catch (Exception e) {
            throwables = Throwables.getCausalChain(e);
        }
        assertThat(throwables.get(0).getClass().isAssignableFrom(ExecutionException.class), is(true));
        assertThat(throwables.get(1).getClass().isAssignableFrom(FileNotFoundException.class), is(true));
        executor.shutdownNow();
    }

    /**
     * {@link com.google.common.base.Throwables#getRootCause(Throwable)}测试
     *
     * @throws Exception
     */
    @Test
    public void testGetRootCause() throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Throwable cause = null;
        final String nullString = null;
        Callable<String> stringCallable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return nullString.substring(0, 2);
            }
        };
        Future<String> stringFuture = executor.submit(stringCallable);
        try {
            stringFuture.get();
        } catch (Exception e) {
            cause = Throwables.getRootCause(e);
        }
        assertThat(cause.getClass().isAssignableFrom(NullPointerException.class), is(true));
        executor.shutdownNow();
    }

}
