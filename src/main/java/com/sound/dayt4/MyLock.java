package com.sound.dayt4;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: ZouTai
 * @date: 2018/4/9
 * @description: 使用AQS实现自己的可重入锁
 * 具体可查看RentrantLock源码
 */
public class MyLock implements Lock {

    private final Sync sync = new Sync();


    class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int arg) {
            /**
             * 如果第一个线程进来，可以拿到锁，因此我们可以返回true
             * 如果第二个线程进来，则拿不到锁，返回false。有种特例，如果当前进来的线程和当前保存的线程是同一个线程，则可以拿到锁，但是有代价，要更新状态值
             * 如何判断是第一个线程进来还是其他线程进来？
             */
            Thread currentThread = Thread.currentThread();
            // 这个地方不能再使用state，否则会和父类的state冲突/错误解释可能-.-
            int c = getState();
            if (c == 0) {
                if (compareAndSetState(c, arg)) {
                    setExclusiveOwnerThread(currentThread);
                }
                return true;
            } else if (currentThread == getExclusiveOwnerThread()) {
                int nextc = c + arg;
                if (nextc < 0) {// overflow
                    throw new Error("Maximum lock count exceeded");
                }
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            int c = getState() - arg;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                setExclusiveOwnerThread(null);
                free = true;
            }
            setState(c);
            return free;
        }

        public Condition newCondition() {
            return newCondition();
        }
    }

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }


}
