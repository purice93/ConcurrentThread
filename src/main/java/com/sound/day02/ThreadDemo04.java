package com.sound.day02;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式4-待返回值的线程
 */
public class ThreadDemo04 implements Callable<Integer> {
    public static void main(String[] args) throws Exception {
        ThreadDemo04 t = new ThreadDemo04();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(t);

        Thread thread = new Thread(futureTask);
        thread.start();

        System.out.println("主线程的任务。。。");

        int result = futureTask.get();
        System.out.println("副线程结果为：" + result);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("副线程任务。。。");
        Thread.sleep(1000);
        return 1;
    }
}
