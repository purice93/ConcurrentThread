package com.sound.day05;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 懒汉式：不初始化实例，在用的时候才初始化（线程不安全）
 */
public class Singleton2 {
    private Singleton2() {
    }

    private static Singleton2 singleton = null;

    /**
     * 双重检查加锁-或者叫做细粒度锁
     * 偏向锁-》轻量级锁-》重量级锁
     *
     * @return
     */
    public static Singleton2 getInstance() {
        //自旋=while(true)占用cpu
        if (singleton == null) { // 读取时不会冲突
            synchronized (Singleton2.class) {
                // 之所以再次判断，是为了防止其他线程已经更改了
                if (singleton == null) {
                    singleton = new Singleton2(); // 指令重排序

                    // 申请一块内存空间   // 1
                    // 在这块空间里实例化对象  // 2
                    // instance的引用指向这块空间地址   // 3
                }
            }
        }
        return singleton;
    }
}
