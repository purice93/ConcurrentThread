package com.sound.dayt9;

/**
 * @author: ZouTai
 * @date: 2018/4/9
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        ConditionBoundedBuffer tmall = new ConditionBoundedBuffer();

        PushTarget p = new PushTarget(tmall);
        TakeTarget t = new TakeTarget(tmall);

        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();
        new Thread(p).start();

        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
        new Thread(t).start();
    }
}
