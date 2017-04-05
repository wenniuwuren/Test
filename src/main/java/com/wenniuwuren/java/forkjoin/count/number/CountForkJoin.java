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

            // 拆分执行子任务
            leftCount.fork();
            rightCount.fork();

            int leftResult = leftCount.join();
            int rightResult = rightCount.join();

            // 合并结果
            sum = leftResult + rightResult;
        }

        return sum;
    }

    public static void main(String[] args) {
        try {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            CountForkJoin countForkJoin = new CountForkJoin(1, 5);

            Future<Integer> result = forkJoinPool.submit(countForkJoin);

            if(countForkJoin.isCompletedAbnormally()) {
                System.out.println(countForkJoin.getException());
            }

            System.out.println(result.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

