package com.hxgz.config;

/**
 * @author Jackson
 * @description 线程类
 * @date 2019/6/10
 */
public class MyThread extends Thread{

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + ":" + i);
        }
    }
}
