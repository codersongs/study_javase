package com.codersongs.javase.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * CountDownLatch与CyclicBarrier都是用于控制并发的工具类，都可以理解成维护的就是一个计数器，但是这两者还是各有不同侧重点的：
 *
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；而CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；CountDownLatch强调一个线程等多个线程完成某件事情。CyclicBarrier是多个线程互等，等大家都完成，再携手共进。
 * 调用CountDownLatch的countDown方法后，当前线程并不会阻塞，会继续往下执行；而调用CyclicBarrier的await方法，会阻塞当前线程，直到CyclicBarrier指定的线程全部都到达了指定点的时候，才能继续往下执行；
 * CountDownLatch方法比较少，操作比较简单，而CyclicBarrier提供的方法更多，比如能够通过getNumberWaiting()，isBroken()这些方法获取当前多个线程的状态，并且CyclicBarrier的构造方法可以传入barrierAction，指定当所有线程都到达时执行的业务功能；
 * CountDownLatch是不能复用的，而CyclicLatch是可以复用的。
 */
public class ConcurrentTools {
    /**
     * 只有在countDown降低到0后，await的阻塞才被取消
     * @throws InterruptedException
     */
    @Test
    public void countDownLatch() throws InterruptedException {
        CountDownLatch mainSignal = new CountDownLatch(1);
        CountDownLatch startSignal = new CountDownLatch(6);
        //用来表示裁判员需要维护的是6个运动员
        CountDownLatch endSignal = new CountDownLatch(6);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.execute(() -> {
                try {
                    //就位，并等待发令
                    System.out.println(Thread.currentThread().getName() + " 运动员等待裁判员响哨！！！");
                    startSignal.countDown();
                    mainSignal.await();
                    System.out.println(Thread.currentThread().getName() + "正在全力冲刺");
                    System.out.println(Thread.currentThread().getName() + "  到达终点");
                    endSignal.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        //等待运动员就位
        startSignal.await();
        //发令
        mainSignal.countDown();
        System.out.println("裁判员发号施令啦！！！");
        startSignal.countDown();
        endSignal.await();
        System.out.println("所有运动员到达终点，比赛结束！");
        executorService.shutdown();
    }

    /**
     *
     */
    @Test
    public void cyclicBarrier(){
        //指定必须有6个运动员到达才行
        CyclicBarrier barrier = new CyclicBarrier(6, () -> {
            System.out.println("所有运动员入场，裁判员一声令下！！！！！");
        });
        System.out.println("运动员准备进场，全场欢呼............");
        ExecutorService service = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " 运动员，进场");
                    barrier.await();
                    System.out.println(Thread.currentThread().getName() + "  运动员出发");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    /**
     * 信号量，用于控制并发访问多个资源，在main方法里避免线程主线程退出
     */
    @Test
    public void semaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(5);
        //表示50个学生
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            service.execute(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "  同学准备获取笔......");
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "  同学获取到笔");
                    System.out.println(Thread.currentThread().getName() + "  填写表格ing.....");
                    TimeUnit.SECONDS.sleep(3);
                    semaphore.release();
                    System.out.println(Thread.currentThread().getName() + "  填写完表格，归还了笔！！！！！！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        service.shutdown();

        Thread.sleep(10000);
    }

    /**
     * exchanger用于线程间交换信息
     * @throws InterruptedException
     */
    @Test
    public void exchanger() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger();
        //代表男生和女生
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(() -> {
            try {
                //男生对女生说的话
                String girl = exchanger.exchange("我其实暗恋你很久了......");
                System.out.println("女孩儿说：" + girl);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.execute(() -> {
            try {
                System.out.println("女生慢慢的从教室你走出来......");
                TimeUnit.SECONDS.sleep(3);
                //男生对女生说的话
                String boy = exchanger.exchange("我也很喜欢你......");
                System.out.println("男孩儿说：" + boy);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread.sleep(10000);
    }
}
