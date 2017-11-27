package cn.xyz.chaos.guava.common.cache;

import cn.xyz.chaos.guava.common.model.Book;

import com.google.common.cache.RemovalNotification;

/**
 *
 * @author lvchenggang
 *
 */
public class BookRemovalListener extends BaseRemovalListener<String, Book> {

    @Override
    public void onRemoval(RemovalNotification<String, Book> notification) {
        this.removalCause = notification.getCause();
        this.removedKey = notification.getKey();
        this.removedValue = notification.getValue();
    }
}
