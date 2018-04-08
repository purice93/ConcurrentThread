package com.sound.day02;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式2-继承Runnable接口-中断线程
 */
public class ThreadDemo02 implements Runnable {
    @Override
    public void run() {
        System.out.println("use runnable...");
    }

    public static void main(String[] args) {
        Thread t = new Thread(new ThreadDemo02());
        t.start();
    }
}
