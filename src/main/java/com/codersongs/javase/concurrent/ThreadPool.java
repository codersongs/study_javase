package com.codersongs.javase.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 线程池，Java 中 Executors实现了多重线程池的实现策略
 * 线程池的根接口是Executor，只有一个execute方法，ExecutorService继承了Executor接口，并扩展了一些方法，AbstractExecutorService
 * 对ExecutorService进行了基本的实现，ThreadPoolExecutor继承了AbstractExecutorService并实现了具体的线程池，ScheduledExecutorService
 * 继承了ThreadPoolExecutor并实现了ScheduledExecutorService接口
 *
 * 提交到线程池中的Runable最终被封装为Worker，worker实现了Runable接口，具备执行线程的能力，其实本质上执行的是自定义runable的run方法
 * 每次worker执行完所有的queue中的任务后都会将worker从set中删除，在getTask中判断是否允许核心线程池关闭或是否超出核心线程数，核心线程和非核心线程是一样的本质上
 * Worker继承了AQS类，主要用于shutdown方法，多线程并发shutdown需要获取锁
 * 核心变量
 *
 * 核心方法
 */
public class ThreadPool {
    @Test
    public void testCtl(){
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-1 << 29));

        System.out.println((-1 << 29) & (~((1 << 29) - 1)));
    }
    /**
     * FixedThreadPool
     */
    @Test
    public void fixedThreadPool(){
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("fixed Thread");
            }
        });
        threadPool.shutdown();
    }

    /**
     * CachedThreadPool
     */
    @Test
    public void cachedThreadPool(){
        ExecutorService threadPool = Executors.newCachedThreadPool();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("cached pool");
            }
        });

        threadPool.shutdown();
    }

    /**
     * SingleThreadExecutor
     */
    @Test
    public void singleThreadPool(){
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("single thread executor");
            }
        });
    }

    @Test
    public void scheduledThreadPool(){
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(10);
    }

    /**
     * 自定义实现线程池
     */
    @Test
    public void selfDefine(){
        //阿里巴巴编程规范要求自定义实现线程池
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        threadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world...");
            }
        });

        threadPoolExecutor.shutdown();
//        threadPoolExecutor.shutdownNow();
    }
}
