package com.sound.daytb4;

/**
 * 1、join实现等待
 */
public class JoinCountDownLatchTest {
    int a = 1;
    int b = 2;
    int c = 0;
    public static void main(String[] args) throws InterruptedException {
        JoinCountDownLatchTest demo = new JoinCountDownLatchTest();
        Thread cal1 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.a = demo.a + 10;
                System.out.println("计算第一部分，结果为a = "+demo.a);
            }
        });
        Thread cal2 = new Thread(new Runnable() {
            @Override
            public void run() {
                demo.b = demo.b + 100;
                System.out.println("计算第二部分，结果为b = "+demo.b);
            }
        });
        cal1.start();
        cal2.start();
        cal1.join();
        cal2.join();
        System.out.println("等待前两部分计算完...");
        System.out.println("将第一部分和第二部分相加为，c = "+ (demo.a+demo.b));
    }
    /**
     *
     计算第一部分，结果为a = 11
     计算第二部分，结果为b = 102
     等待前两部分计算完...
     将第一部分和第二部分相加为，c = 113
     */
}