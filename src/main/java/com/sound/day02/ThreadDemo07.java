package com.sound.day02;

import java.util.Arrays;
import java.util.List;

/**
 * @author: ZouTai
 * @date: 2018/4/8
 * @description: 多线程的实现方式5-lambda表达式
 */
public class ThreadDemo07 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 20, 30, 40);
        int result = new ThreadDemo07().add(list);
        System.out.println("结果为：" + result);
    }

    private int add(List<Integer> list) {
        // lamdba表达式使用，jdk1.8开始
        return list.parallelStream().mapToInt(i -> i * 2).sum();
    }
}
