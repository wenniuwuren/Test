package com.wenniuwuren.java.forkjoin.count.number;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 计算 1+2+3+..+n 的结果
 * Created by hzzhuyibin on 2017/4/1.
 */
public class CountForkJoin extends RecursiveTask<Integer>{

    private static final int THRESHOLD = 2;

    private int start;

    private int end;

    public CountForkJoin(int start, int end) {
        this.start = start;
        this.end = end;
    }


    /**
     * ForkJoinTask与一般的任务的主要区别在于它需要实现compute方法，在这个方法里，首先需要判断任务是否足够小，如果足够小就直接执行任务。
     * 如果不足够小，就必须分割成两个子任务，每个子任务在调用fork方法时，又会进入compute方法，
     * 看看当前子任务是否需要继续分割成孙任务，如果不需要继续分割，则执行当前子任务并返回结果。使用join方法会等待子任务执行完并得到其结果。
     * @return
     */
    @Override
    protected Integer compute() {

        int sum = 0;

        // 如果任务足够小就直接计算任务
        boolean canComputeDirectly = (end - start) <= THRESHOLD;

        if (canComputeDirectly) {
            while (start <= end) {
                sum += start;
                start++;
            }
        } else {
            int middle = (start + end) / 2;
            CountForkJoin leftCount = new CountForkJoin(start, middle);
            CountForkJoin rightCount = new CountForkJoin(middle + 1, end);

            // 分割子任务
            leftCount.fork();
            rightCount.fork();

            // 任务合并
            int leftResult = leftCount.join();
            int rightResult = rightCount.join();
            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            CountForkJoin countForkJoin = new CountForkJoin(1, 5);

            Future<Integer> result = forkJoinPool.submit(countForkJoin);

            // ForkJoinTask在执行的时候可能会抛出异常，但是我们没办法在主线程里直接捕获异常，
            // 所以ForkJoinTask提供了isCompletedAbnormally()方法来检查任务是否已经抛出异常或已经被取消了，
            // 并且可以通过ForkJoinTask的getException方法获取异常。
            if(countForkJoin.isCompletedAbnormally()) {
                System.out.println(countForkJoin.getException());
            }

            System.out.println(forkJoinPool.invoke(countForkJoin));
            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

