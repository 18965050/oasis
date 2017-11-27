package cn.xyz.chaos.guava.common.concurrent;

import java.io.FileNotFoundException;

import com.google.common.util.concurrent.FutureFallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

/**
 * FutureFallback 实现类
 *
 * @author lvchenggang
 *
 */
public class FutureFallbackImpl implements FutureFallback<String> {

    @Override
    public ListenableFuture<String> create(Throwable t) throws Exception {
        if (t instanceof FileNotFoundException) {
            SettableFuture<String> settableFuture = SettableFuture.create();
            settableFuture.set("Not Found");
            return settableFuture;
        }
        throw new RuntimeException(t);
    }
}
