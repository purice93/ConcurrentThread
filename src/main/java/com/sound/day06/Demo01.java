package com.sound.day06;

/**
 * @author: ZouTai
 * @date: 2018/4/9
 * @description: 锁重入
 */
public class Demo01 {

    /**
     * 第一次进入demo01
     */
    public synchronized void a () {
        /**
         * 第2次进入demo01，重入：同一对象，可访问
         */
        System.out.println("a");
        b();
    }
    public synchronized void b() {
        System.out.println("b");
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
