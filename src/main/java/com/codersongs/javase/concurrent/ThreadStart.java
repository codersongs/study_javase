package com.codersongs.javase.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

/**
 * 实现线程的方式
 */
public class ThreadStart {
    /**
     * Runable其实不具备线程的开启和执行能力，它仅仅是提供了一个run方法，作为线程的规范，真正实现线程执行能力的是Thread类，start0方法是native的，真正实现
     * 线程的执行能力，因此所有多线程的执行最后都无法绕开Thread方法
     */
    @Test
    public void startByThread(){
        new TaskThread().start();
    }

    @Test
    public void startByRunnable(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("runable");
            }
        }).start();
    }

    /**
     * callable 的使用必须结合线程池，可以直接提交到线程池返回Future，也可以先传递给FutureTask，将FutureTask传递给线程池
     * callable的返回值靠FutureTask来获取,FutureTask实现了RunableFuture接口，RunableFuture继承了Runnable和Future接口
     * Callable本身不具有执行线程的能力，Future实现了Runnable接口，可以作为参数传递给Thread，因此具备执行线程的能力，FutureTask
     * 的run 方法调用了callable的call方法，并且拿到了call方法的返回值并放到属性outcome中，这样就可以通过futureTask来实现获取返回值了
     * @throws Exception
     */
    @Test
    public void startByCallable() throws  Exception{
        Callable callable = new CallableTask();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //submit的内部实现也是调用了execute方法，但是submit可以传递FutureTask参数，从而可以默认实现回值
        Future<String> future = threadPool.submit(callable);
        System.out.println(future.get());

        FutureTask<String> futureTask = new FutureTask<>(callable);
        threadPool.execute(futureTask);

        System.out.println(futureTask.get());

        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }

}
class CallableTask implements Callable<String>{

    @Override
    public String call() throws Exception {
        System.out.println("execute call");
        return "HEllO";
    }
}
class TaskThread extends Thread{
    @Override
    public void run(){
        System.out.println("run");
    }
}
