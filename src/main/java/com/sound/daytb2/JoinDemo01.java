package com.sound.daytb2;

/**
 * @author: ZouTai
 * @date: 2018/4/10
 * @description: join-加塞线程，即，向线程加入内部线程，且内部线程，执行完，主线程才能继续。
 * 相当于将加塞线程合并入主线程
 *
 *
 */
public class JoinDemo01 {
    public void a(Thread joinThread){
        System.out.println("线程a开始...");
        joinThread.start();
        try {
            joinThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程a执行完毕");
    }

    public void b() {
        System.out.println("加塞线程开始执行...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("加塞线程执行完毕");
    }

    public static void main(String[] args) {
        JoinDemo01 joinDemo01 = new JoinDemo01();
        Thread jThread = new Thread() {
            @Override
            public void run() {
                joinDemo01.b();
            }
        };
        joinDemo01.a(jThread);
    }

    /** result is :
     线程a开始...
     加塞线程开始执行...
     加塞线程执行完毕
     线程a执行完毕
     */
}
