package com.sound.daytd5;

import java.util.concurrent.locks.StampedLock;

/**
 * @author: ZouTai
 * @date: 2018/4/17
 * @description:
 */
public class StampedLockDemo {
    private int balance;
    private final StampedLock stampedLock = new StampedLock();


    /**
     * 1、悲观写
     * writeLock()：典型的cas操作，如果STATE等于s,设置写锁位为1（s+WBIT）。
     * acquireWrite跟acquireRead逻辑类似，先自旋尝试、加入等待队列、直至最终Unsafe.park()挂起线程。
      */
    public void write(int i) {
        long stamp = stampedLock.writeLock();
        balance += i;
        stampedLock.unlockWrite(stamp);
    }

    /**
     * 2、悲观读
     * 乐观锁失败后锁升级为readLock()：尝试state+1,用于统计读线程的数量，
     * 如果失败，进入acquireRead()进行自旋，通过CAS获取锁。
     * 如果自旋失败，入CLH队列，然后再自旋，
     * 如果成功获得读锁，激活cowait队列中的读线程Unsafe.unpark(),
     * 最终依然失败，Unsafe().park()挂起当前线程。
     */
    public void read() {
        long stamp = stampedLock.readLock();
        int value = balance;
        stampedLock.unlockRead(stamp);
    }

    /**重点：！！！
     * 3、乐观读：当读取远远大于写入时，使用乐观锁
     * tryOptimisticRead()：如果当前没有写锁占用，返回state(后7位清0，即清0读线程数)，如果有写锁，返回0，即失败。
     */
    public void optimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int value = balance;
        // 校验这个戳是否有效validate()：比较当前stamp和发生乐观锁得到的stamp比较，不一致则失败。
        if(!stampedLock.validate(stamp)) {
            long readStamp = stampedLock.readLock();
            value = balance;
            stamp = readStamp;
        }
        stampedLock.unlockRead(stamp);
    }

    /**重点：！
     * 4、判断条件之后，再写
     * 存在读取和写入两个操作
     */
    public void conditionReadWrite(int state){
        // 首先读取
        long stamp = stampedLock.readLock();
        while (balance > 100){
            long writeStamp = stampedLock.tryConvertToWriteLock(stamp);
            // 步骤：a
            if(writeStamp!=0) {
                balance += state;
                stamp = writeStamp;
                break;
            } else {
                // 转换失败
                stampedLock.unlockRead(stamp);
                //显式直接进行写锁 然后再通过循环再试,回到 步骤：a
                stamp = stampedLock.writeLock();
            }
        }
    }

}
