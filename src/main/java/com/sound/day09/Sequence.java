package com.sound.day09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {
    private int value = 0;
    Lock lock = new ReentrantLock();

    /**
     * @return
     */
    public int getNext() {
        lock.lock(); //加锁
        {
        // 代码块
            value++;
        }
        lock.unlock(); //释放锁
        return value;
    }

    public static void main(String[] args) {

        Sequence s = new Sequence();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " " + s.getNext());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

}
