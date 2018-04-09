package com.sound.dayt9;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ZouTai
 * @date: 2018/4/9
 * @description:
 */
public class ConditionBoundedBuffer {
    private Lock lock = new ReentrantLock();
    private Condition product = lock.newCondition();
    private Condition consume = lock.newCondition();
    private final int max = 10;
    int count = 0;

    public void put() throws InterruptedException {
        lock.lock();
        try {
            while (count >= max) {
                System.out.println(Thread.currentThread().getName()+"生产过量，停止生产...");
                product.await();
            }
            count++;
            consume.signal();
            System.out.println(Thread.currentThread().getName()+"生产-库存变为："+count);
        } finally {
            lock.unlock();
        }

    }
    public void take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println(Thread.currentThread().getName()+"库存为空，无法购买...");
                consume.await();
            }
            count--;
            System.out.println(Thread.currentThread().getName()+"消费-库存还剩："+count);
            product.signal();
        } finally {
            lock.unlock();
        }
    }
}
