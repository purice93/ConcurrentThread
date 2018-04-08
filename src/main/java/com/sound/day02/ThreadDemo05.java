package com.sound.day02;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式5-定时器
 */
public class ThreadDemo05 {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("timer ....");
            }
        },0,2000);
    }
}
