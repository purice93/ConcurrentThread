package com.sound.day05;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 饿汉式：初始化实例(线程一定安全)
 */
public class Singleton {
    /**
     * 1、构造方法私有
     */
    private Singleton(){}

    /**
     * 2、建立静态对象，单个
     */
    public static Singleton singleton = new Singleton();

    public Singleton getInstance() {
        return singleton;
    }
}
