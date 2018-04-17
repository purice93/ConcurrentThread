package com.sound.daytd5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: ZouTai
 * @date: 2018/4/17
 * @description:
 */
public class LongAdderDemo {
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        LongAdder counter = new LongAdder();
        ExecutorService executorService = Executors.newFixedThreadPool(8);

        int numberOfThreads = 4;
        int numberOfIncrements = 100;

        Runnable incrementAction = new Runnable() {
            @Override
            public void run() {
                System.out.println("sum="+counter.sum());
//                System.out.println("value=");
                counter.add(-77);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter.increment();
            }
        };

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.execute(incrementAction);
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(counter.sum());
//        assertEquals(counter.sum(), numberOfIncrements * numberOfThreads);
//        assertEquals(counter.sumThenReset(), numberOfIncrements * numberOfThreads);
//        assertEquals(counter.sum(), 0);

    }
}
