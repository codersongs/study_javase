package com.codersongs.javase.concurrent;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ConcurrentLock {
    /**
     * 重入锁
     */
    @Test
    public void reentrantLock(){
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        try {
            lock.lock();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 读写锁
     */
    @Test
    public void readWriteLock(){
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readWriteLock.readLock();
        readWriteLock.writeLock();
    }
}
