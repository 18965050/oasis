package cn.xyz.chaos.jakarta.common.collection;

import static org.hamcrest.CoreMatchers.is;

import java.util.Comparator;
import java.util.Iterator;

import org.apache.commons.collections.Buffer;
import org.apache.commons.collections.BufferOverflowException;
import org.apache.commons.collections.buffer.BlockingBuffer;
import org.apache.commons.collections.buffer.BoundedFifoBuffer;
import org.apache.commons.collections.buffer.PriorityBuffer;
import org.apache.commons.collections.buffer.UnboundedFifoBuffer;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.collections.Buffer}单元测试
 * <p />
 * Buffer继承自Collection,提供了按某种排序顺序获取或去除集合成员的功能
 *
 * @author lvchenggang
 *
 */
public class BufferTest {

    /**
     * {@link org.apache.commons.collections.buffer.UnboundedFifoBuffer}测试
     */
    @Test
    public void testUnbounded() {
        Buffer buffer = new UnboundedFifoBuffer();
        buffer.add("aaa");
        buffer.add("bbb");
        buffer.add("ccc");
        Assert.assertThat((String) buffer.remove(), is("aaa"));
        buffer.add("ddd");
        Assert.assertThat((String) buffer.remove(), is("bbb"));
    }

    /**
     * {@link org.apache.commons.collections.buffer.BoundedFifoBuffer}测试
     */
    @Test(expected = BufferOverflowException.class)
    public void testBounded() {
        Buffer buffer = new BoundedFifoBuffer(2);
        buffer.add("aaa");
        buffer.add("bbb");
        buffer.add("ccc");
    }

    /**
     * {@link org.apache.commons.collections.buffer.PriorityBuffer}测试
     */
    @Test
    public void testPriority() {
        Buffer buffer = new PriorityBuffer(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        });
        buffer.add(Integer.valueOf(5));
        buffer.add(Integer.valueOf(4));
        buffer.add(Integer.valueOf(8));
        buffer.add(Integer.valueOf(1));
        /**
         * 这里需要注意: PriorityBuffer中元素的排序只是两两排序, 而不是整改集合排序.其排序过程为: <br />
         *
         * <pre>
         * 5
         * 4 5
         * 4 8 5
         * 1 4 8 5
         * </pre>
         */
        Iterator<Integer> iter = buffer.iterator();
        Assert.assertThat(iter.next(), is(1));
        Assert.assertThat(iter.next(), is(4));
        Assert.assertThat(iter.next(), is(8));
        Assert.assertThat(iter.next(), is(5));
    }

    /**
     * {@link org.apache.commons.collections.buffer.BlockingBuffer}测试
     */
    @Test
    public void testBlocking() {
        class BufferListener implements Runnable {
            private Buffer buffer;

            public BufferListener(Buffer buffer) {
                this.buffer = buffer;
            }

            @Override
            public void run() {
                while (buffer.size() > 1) {
                    buffer.remove();
                }
            }
        }

        Buffer buffer = BlockingBuffer.decorate(new UnboundedFifoBuffer());
        Thread thread = new Thread(new BufferListener(buffer));
        thread.start();
        buffer.add("aaa");
        buffer.add("bbb");
        buffer.add("ccc");
        Assert.assertThat((String) buffer.get(), is("aaa"));
    }
};
