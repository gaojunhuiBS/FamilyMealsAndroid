package com.example.gaojunhui.textworld;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gaojunhui on 2017/5/4.
 */
public class ThreadPoolManager {
    private ExecutorService service;
    private static volatile ThreadPoolManager ourInstance = null;

    public static ThreadPoolManager getInstance() {
        if (null == ourInstance) {
            synchronized (ThreadPoolManager.class) {
                if (null == ourInstance) {
                    ourInstance = new ThreadPoolManager();
                }
            }
        }
        return ourInstance;
    }

    private ThreadPoolManager() {
        service = Executors.newCachedThreadPool();
    }

    public void addTask(Runnable runnable) {
        service.submit(runnable);
    }
}
