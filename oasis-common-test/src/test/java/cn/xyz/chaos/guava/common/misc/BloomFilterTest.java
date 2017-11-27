package cn.xyz.chaos.guava.common.misc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

/**
 * {@link com.google.common.hash.BloomFilter}单元测试
 * <p />
 * <ui> BloomFilter是一种计算方法,其遵循如下判断规则:
 * <li>如果检测请求返回有,则该元素可能在集合内,也可能不在</li>
 * <li>如果检测请求返回无,则该元素一定不在集合内</li> </ui> <br />
 * 更多详情请参见 http://baike.baidu.com/view/1912944.htm?fr=aladdin
 *
 * @author lvchenggang
 *
 */
public class BloomFilterTest {

    private BloomFilter<BigInteger> bloomFilter;
    private Random random;
    private int numBits;
    private List<BigInteger> stored;
    private List<BigInteger> notStored;

    @Before
    public void setUp() {
        numBits = 128;
        random = new Random();
        stored = new ArrayList<BigInteger>();
        notStored = new ArrayList<BigInteger>();
        loadBigIntList(stored, 1000);
        loadBigIntList(notStored, 100);
    }

    /**
     * 出错几率判断.<br />
     * 注意:这个单元测试可能不通过
     */
    @Test
    public void testMayContain() {
        setUpBloomFilter(stored.size());
        int falsePositiveCount = 0;
        for (BigInteger bigInteger : notStored) {
            boolean mightContain = bloomFilter.mightContain(bigInteger);
            boolean isStored = stored.contains(bigInteger);
            if (mightContain && !isStored) {
                falsePositiveCount++;
            }
        }
        assertThat(falsePositiveCount < 10, is(true));
    }

    @Test
    public void testMayContainGoOverInsertions() {
        setUpBloomFilter(50);
        int falsePositiveCount = 0;
        for (BigInteger bigInteger : notStored) {
            boolean mightContain = bloomFilter.mightContain(bigInteger);
            boolean isStored = stored.contains(bigInteger);
            if (mightContain && !isStored) {
                falsePositiveCount++;
            }
        }
        assertThat(falsePositiveCount, is(notStored.size()));
    }

    private void setUpBloomFilter(int numInsertions) {
        bloomFilter = BloomFilter.create(new BigIntegerFunnel(), numInsertions);
        addStoredBigIntegersToBloomFilter();
    }

    private BigInteger getRandomBigInteger() {
        return new BigInteger(numBits, random);
    }

    private void addStoredBigIntegersToBloomFilter() {
        for (BigInteger bigInteger : stored) {
            bloomFilter.put(bigInteger);
        }
    }

    private void loadBigIntList(List<BigInteger> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(getRandomBigInteger());
        }
    }

    private class BigIntegerFunnel implements Funnel<BigInteger> {
        @Override
        public void funnel(BigInteger from, PrimitiveSink into) {
            into.putBytes(from.toByteArray());
        }
    }

}
