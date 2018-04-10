package com.sound.daytb3;

/**
 * @author: ZouTai
 * @date: 2018/4/10
 * @description: ThreadLOcal-线程局部变量
 * 即，每个线程将维护一个自己的变量，这个变量只对当前线程可以随意更改，其他线程不会影响当前线程变量的值
 */
public class ThreadLocalDemo {
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            // 初始化值为0
            return new Integer(0);
        }
    };

    public int getNext() {
        int count = threadLocal.get();
        count++;
        threadLocal.set(count);
        return count;
    }

    public static void main(String[] args) {
        ThreadLocalDemo tld = new ThreadLocalDemo();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "" + tld.getNext());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println(Thread.currentThread().getName() + "" + tld.getNext());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
