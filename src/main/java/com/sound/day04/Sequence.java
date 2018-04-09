package com.sound.day04;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: synchronized的用法
 */
public class Sequence {
    private int value = 0;
    private static int sValue = 0;

    /**
     * 1、放在普通类方法上，内置锁就是实例对象
     */
    public synchronized int getNext() {
        return value++;
    }

    /**
     * 2、修饰静态方法，内置锁为当前的class字节码对象
     * Sequence.class
     * @return
     */
    public static synchronized int getPrevious() {
        return sValue--;
    }

    public int getMid() {
        /**
         * 3、修饰代码块，内置锁为括号里的对象
         */
        // monitorenter
        synchronized (this) {
            return value++;
        }
        // monitorexit
    }

    public static void main(String[] args) {
        Sequence s = new Sequence();

        for (int j = 0; j < 3; j++) {
            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        System.out.println(Thread.currentThread().getName() + s.getNext());
                        try {
                            sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        }
    }
}
