package com.sound.daytd5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: ZouTai
 * @date: 2018/4/16
 * @description:
 */
public class PoolDemo {
    public static void main(String[] args) {
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(10,10,0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
//        ExecutorService pool = Executors.newFixedThreadPool(10); 固定线程池大小
        ExecutorService pool = Executors.newCachedThreadPool(); // 不固定大小，自由伸缩
        while (true) {
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }
    }
}
