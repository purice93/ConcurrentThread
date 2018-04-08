package com.sound.day01;

/**
 * @author: ZouTai
 * @date: 2018/4/3
 * @description:
 */
public class MyThread extends Thread {
    @Override
    public synchronized void run() {
        while (true) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("副线程运行...");
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread);
        thread.start();

        while (true) {
            synchronized (myThread) {
                System.out.println("主线程运行...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myThread.notifyAll();
            }
        }

    }
}
 