package com.sound.daytb4;

import java.util.concurrent.CountDownLatch;

/**
 * 2、countDownLatch实现等待
 */
public class CountDownLatchTest {
    /**
     * 初始化为2
     */
    static CountDownLatch countDownLatch = new CountDownLatch(2);
    int a = 1;
    int b = 2;
    int c = 0;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatchTest demo = new CountDownLatchTest();
        new Thread(new Runnable() {
            @Override
            public void run() {
                demo.a = demo.a + 10;
                System.out.println("计算第一部分，结果为a = "+demo.a);
                countDownLatch.countDown();
                demo.b = demo.b + 100;
                System.out.println("计算第二部分，结果为b = "+demo.b);
                countDownLatch.countDown();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("继续计算其他任务");
            }
        }).start();
        countDownLatch.await();
        System.out.println("等待前两部分计算完...");
        System.out.println("将第一部分和第二部分相加为，c = "+ (demo.a+demo.b));
    }

    /**
     *
     计算第一部分，结果为a = 11
     计算第二部分，结果为b = 102
     等待前两部分计算完...
     将第一部分和第二部分相加为，c = 113
     继续计算其他任务
     */
}