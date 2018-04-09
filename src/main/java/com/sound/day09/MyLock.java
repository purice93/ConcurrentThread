package com.sound.day09;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: ZouTai
 * @date: 2018/4/9
 * @description: 实现自己的可重入lock锁
 */
public class MyLock implements Lock {
    private Thread firstLock = null;
    private boolean isLock = false;
    private int lockCount = 0; //重入的线程个数

    @Override
    public synchronized void lock() {
        Thread currentThread = Thread.currentThread();
        // 当对象已经被第一个线程加锁&&当前进入的线程不是第一个线程，就等待wait
        while (isLock && currentThread != firstLock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 当前线程为第一个线程，修改标记
        isLock = false;
        firstLock = currentThread;
        lockCount++;
    }

    @Override
    public synchronized void unlock() {
        // 判断是否为第一个线程，是的话，计数-1，直达为最后一个线程时，唤醒对象的其他等待线程
        if (Thread.currentThread() == firstLock) {
            lockCount--;

            if (lockCount==0) {
                notify();
                isLock =false;
            }
        }
    }




    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
