package com.codersongs.javase.concurrent;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 并发包基本上都是围绕AQS展开的，AQS使用模板设计模式，将tryAccquire和tryRelease这些方法教给调用者去实现
 */
public class ConcurrentCollection {

    /**
     * ConcurrentHashMap核心实现从JDK的分段锁到通过CAS获取桶的位置锁
     */
    @Test
    public void concurrentHashMap(){
        Map<String, String> map = new ConcurrentHashMap<>();
        map.put("key1", "A");
        map.put("key2", "B");

        System.out.println(map);
    }

    /**
     * ArrayBlockingQueue使用的是一个锁，而LinkedBlockingQueue读写锁分离
     * 两者都使用notEmpty和notFull两个condition
     * @throws Exception
     */
    @Test
    public void blockingQueue() throws Exception{
        ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(10);
        blockingQueue.put("A");
        System.out.println(blockingQueue.take());
        //带头尾指针的单向链表
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        linkedBlockingQueue.put("A");
        System.out.println(linkedBlockingQueue.take());
    }

    /**
     * 使用CAS的方式进行offer和poll
     */
    @Test
    public void concurrentLinkedQueue(){
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(1);
        queue.offer(2);

        queue.poll();
    }

    /**
     * 与读写锁不同的是，写锁会阻塞读操作，COW设计思想使CopyOnWriteArrayList无论何时都不会阻塞读操作
     * 读写分离，写时复制，volatile保证数组修改的可见性
     */
    @Test
    public void copyOnWriteArrayList(){
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("A");
        String s = copyOnWriteArrayList.get(0);
        System.out.println(s);
    }

    /**
     * 核心为threadLocalMap，threadLocalMap是Thread的一个属性，key为threadLocal类型的对象，value为threadLocal设置的值
     * 由于threadLocalMap是default修饰的，因此没法在自定义代码中访问，因此属于线程的私有资源，可以为一个线程设置多个threadLocal对象
     * 这也是threadLocalMap设计的初衷(为什么使用map这种集合而不是一个object)，threadLocal使用的是开放地址法来解决哈希冲突，因为threadLocal
     * 通常来说存放数据比较少，默认长度为16，扩容double，负载因子2/3
     * ThreadLocalMap中Entry的key 为WeakReference，会导致key为null，value不可访问，但是由于被entry引用无法释放的问题，会造成
     * 内存泄露，因此在增加或删除的时候需要检测StaleEntry将entry和value设置为null
     */
    @Test
    public void threadLocal(){
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("Hello");
        String get = threadLocal.get();
        System.out.println(get);


        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        threadLocal2.set("Hello2");
        String get2 = threadLocal2.get();
        System.out.println(get2);
    }
}
