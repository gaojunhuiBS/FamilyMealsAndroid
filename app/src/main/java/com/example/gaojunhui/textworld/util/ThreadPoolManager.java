package com.example.gaojunhui.textworld.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gaojunhui on 2016/12/1.
 * 线程池
 */
public class ThreadPoolManager {
    private ExecutorService service;
    private ThreadPoolManager() {
        service = Executors.newCachedThreadPool();
    }
    private static volatile ThreadPoolManager manager;
    public static ThreadPoolManager getInstance() {
        if (null == manager) {
            synchronized (ThreadPoolManager.class) {
                if (null == manager) {
                    manager = new ThreadPoolManager();
                }
            }
        }
        return manager;
    }
    public void addTask(Runnable runnable) {
        service.submit(runnable);
    }
}
