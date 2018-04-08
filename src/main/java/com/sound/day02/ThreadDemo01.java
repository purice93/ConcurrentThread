package com.sound.day02;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式1-继承Thread-中断线程
 */
public class ThreadDemo01 extends Thread {
    /**
     * 带线程名的构造方法
     */
    public ThreadDemo01(String name) {
        super(name);
    }

    @Override
    public void run() {
        // 判断中断条件
        while (!isInterrupted()) {
            System.out.println(getName() + "线程启动了");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo01 t1 = new ThreadDemo01("线程1");
        ThreadDemo01 t2 = new ThreadDemo01("线程2");


        t1.start();
        t2.start();

        // 线程中断
        t1.interrupt();
    }
}
