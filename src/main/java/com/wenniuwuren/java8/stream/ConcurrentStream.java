package com.wenniuwuren.java8.stream;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * Created by hzzhuyibin on 2017/5/4.
 */
public class ConcurrentStream {

    public static void main(String[] args) {
        // 以下使用reduce可以 并行来提高效率
        System.out.println(measureSumPerf(n -> ConcurrentStream.rangeSum(n), 1_0000_0000));
        System.out.println(measureSumPerf(n -> ConcurrentStream.parallelRangeSum(n), 1_0000_0000));

        // 但是如果使用 foreach 使用parallel()结果都是错的，因为foreach有共享变量total
        System.out.println(measureSumPerf(ConcurrentStream::rangeSumWithForEach, 1_0000_0000));
        System.out.println(measureSumPerf(ConcurrentStream::parallelRangeSumWithForEach, 1_0000_0000));
    }

    /**
     * 计算10次返回最短时间，减少测试误差
     * @param adder
     * @param n
     * @return
     */
    private static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("加的结果，这里用不到，只打印:" + sum);
            if (duration < fastest) {
                fastest = duration;
            }
        }

        return fastest;
    }

    // 返回long，没有拆箱装箱；生成数字范围容易fork
    private static long rangeSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, (a, b) -> a + b);
    }

    private static long parallelRangeSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, (a, b) -> a + b);
    }

    private static long rangeSumWithForEach(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .forEach(accumulator::add);
        return accumulator.total;
    }

    private static long parallelRangeSumWithForEach(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n)
                .parallel()
                .forEach(accumulator::add);
        return accumulator.total;
    }

}

