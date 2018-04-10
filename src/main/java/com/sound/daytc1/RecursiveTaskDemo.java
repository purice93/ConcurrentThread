package com.sound.daytc1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author: ZouTai
 * @date: 2018/4/10
 * @description: ForkJoin模式计算序列相加-二分法
 */
public class RecursiveTaskDemo extends RecursiveTask<Integer> {
    private int first;
    private int last;

    public RecursiveTaskDemo(int first, int last) {
        this.first = first;
        this.last = last;
    }


    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread().getName() + " ... ");
        /**
         * 这里面要写自己的划分逻辑
         * 构造ForkJoin
         */
        int sum = 0;
        // 拆分任务
        if (last - first <= 2) {
            // 计算
            for (int i = first; i <= last; i++) {
                sum += i;
            }
        } else {
            /**
             * 类似于分支递归思想
             */
            RecursiveTaskDemo demo01 = new RecursiveTaskDemo(first, (last + first) / 2);
            RecursiveTaskDemo demo02 = new RecursiveTaskDemo((last + first) / 2 + 1, last);

            // 执行
            demo01.fork();
            demo02.fork();

            Integer a = demo01.join();
            Integer b = demo02.join();

            sum = a + b;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(3);
        Future<Integer> future = forkJoinPool.submit(new RecursiveTaskDemo(1, 100));
        System.out.println("处理其他程序...");
        try {
            System.out.println("计算的值为：" + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
