package com.codersongs.javase.keyword;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Volatile {
    private static volatile boolean isOver = false;
    //由于Atomic类中的value设置为volatile因此变量可以不用声明为volaitle的
    private /*volatile*/ AtomicInteger valAtoic = new AtomicInteger();
    private volatile int a = 0;
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isOver) ;
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //由于对isOver的赋值操作是原子的，因此这是个线程安全的操作
        isOver = true;
    }

    /**
     * 线程安全的
     * @throws InterruptedException
     */
    @Test
    public void testVolatileAtomic() throws InterruptedException {
        Volatile vol = new Volatile();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    vol.valAtoic.incrementAndGet();
                }

            }
        };
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            threadPool.execute(runnable);
        }

        threadPool.shutdown();

        System.out.println(vol.valAtoic.get());
    }

    /**
     * 线程不安全
     * @throws InterruptedException
     */
    @Test
    public void testVolatileInt() throws InterruptedException {
        Volatile vol = new Volatile();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    vol.a ++;
                }

            }
        };
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            threadPool.execute(runnable);
        }

        threadPool.shutdown();

        System.out.println(vol.a);
    }
}
