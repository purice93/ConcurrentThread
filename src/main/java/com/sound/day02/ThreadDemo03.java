package com.sound.day02;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式3-匿名内部类
 */
public class ThreadDemo03 {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("匿名内部类线程。。");
            }
        }.start();
    }
}
