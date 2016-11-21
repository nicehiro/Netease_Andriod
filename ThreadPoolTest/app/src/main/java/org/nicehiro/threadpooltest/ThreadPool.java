package org.nicehiro.threadpooltest;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

/**
 * Created by hiro on 16-11-21.
 */

public class ThreadPool {
    private static final String TAG = "ThreadPool";

    private static class FastTask implements Runnable {

        private String name;

        public FastTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Log.i(TAG, name + " running");
        }

        @Override
        public String toString() {
            return "FastTask: " + name;
        }
    }

    private static class MyThreadFactory implements ThreadFactory {

        private static int count = 0;

        @Override
        public Thread newThread(Runnable runnable) {
            Log.i(TAG, "第 " + (count++) + " 个线程创建");
            return new Thread(runnable);
        }
    }

    public static void runSingleThreadPool() {
        ExecutorService threadPool0 = Executors.newSingleThreadExecutor();
        threadPool0.execute(new FastTask("task0"));
        threadPool0.execute(new FastTask("task1"));
        threadPool0.execute(new FastTask("task2"));
    }

    private static void runSingleThreadPoolWithFactory() {
        ExecutorService threadPool0 = Executors.newSingleThreadExecutor(new MyThreadFactory());
        threadPool0.execute(new FastTask("task0"));
        threadPool0.execute(new FastTask("task1"));
        threadPool0.execute(new FastTask("task2"));
    }
}
