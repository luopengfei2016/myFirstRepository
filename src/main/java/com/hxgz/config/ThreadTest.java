package com.hxgz.config;

/**
 * @author Jackson
 * @description 线程测试类
 * @date 2019/6/10
 */
public class ThreadTest{

    public static void main(String[] args) {

        MyThread T1 = new MyThread("T1");
        MyThread T2 = new MyThread("T2");
        MyThread T3 = new MyThread("T3");


//        直接调用run()方法是同步执行也就不能达到多线程的目的
//        T1.run();
//        T2.run();
//        T3.run();

//        通过start()方法调用run()方法则是异步执行
        try {
            T1.start();
            T1.join();
            T2.start();
            T2.join();
            T3.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

