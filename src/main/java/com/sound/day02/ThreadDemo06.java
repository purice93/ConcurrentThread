package com.sound.day02;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式6-线程池
 */
public class ThreadDemo06 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }
}
