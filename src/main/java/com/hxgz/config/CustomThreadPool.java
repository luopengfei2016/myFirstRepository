package com.hxgz.config;

import java.util.concurrent.BlockingQueue;

/**
 * @author Jackson
 * @description 线程池
 * @date 2019/6/10
 */
public class CustomThreadPool {
    private volatile Integer miniSize = 10;
    private static final Integer maxSize = 5;
    private static final long keepAliveTime = 1;
    private static final BlockingQueue<Runnable> workQueue = null;
}
