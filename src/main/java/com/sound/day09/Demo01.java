package com.sound.day09;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: ZouTai
 * @date: 2018/4/9
 * @description: 锁重入
 */
public class Demo01 {


    Lock lock = new ReentrantLock();

    /**
     * 第一次进入demo01
     */
    public  void a() {
        /**
         * 第2次进入demo01，重入：同一对象，可访问
         */
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public  void b() {

        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo01 demo01 = new Demo01();
        demo01.a();
    }

    /**
     * the result is :
     * a
     * b
     */
}
