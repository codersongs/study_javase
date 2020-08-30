package com.codersongs.javase.collection;

import org.junit.Test;

import java.util.*;

public class BasicCollection {
    public static void main(String[] args) {

    }

    /**
     * ArrayList是最常用的集合
     * 动态扩容：size << 1
     * 初始化长度为0，第一次扩容为10
     * 随机读取快，删除和移动元素慢，适用于读多邪少的场景，空间必须是连续的，单个元素占用空间小，但是长度有限制，也会造成一部分空间浪费
     * ListIterator是List特有的可以双向遍历next和previous，除了具有常规的删除功能以外，还有添加和替换功能
     */
    @Test
    public void arrayList(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(list);
        ListIterator<String> listIterator = list.listIterator();
        //指向第一个，调用next，返回当前指向下一个
//        listIterator.next();
        System.out.println(listIterator.next());
//        listIterator.set("1Update");
        //先指向前一个，然后返回
//        listIterator.previous();
        System.out.println(listIterator.previous());

//        System.out.println(listIterator.next());
//        //替换访问过的最后一个
        listIterator.set("update");
        //在访问过的最后一个元素之后添加
        listIterator.add("add");
//        System.out.println(listIterator.previous());
//        System.out.println(listIterator.next());
//        System.out.println(listIterator.previous());
    }

    /**
     * LinkedList是一个带头尾指针的双向链表
     * 随机读取慢，插入，删除快，适用于读取少，修改多的情况，空间占用更大但是没有空间限制和多余空间浪费，不要求空间连续
     */
    @Test
    public void linkedList(){
        List<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        System.out.println(linkedList);
    }

    /**
     * Vector 是线程安全的，因此比较低效，使用较少
     * 无参构造器默认长度为10，可以指定扩容长度，不指定的话double
     */
    @Test
    public void vector(){
        Vector<String> vector = new Vector<>();
        vector.add("Hello");
        System.out.println(vector);
    }

    /**
     * 队列是一种先进先出的数据结构，Java对队列接口的实现比较特殊，双向队列Deque继承自Queue，而LinkedList同时实现了链表（双向链表），队列（双端队列）
     * 总结下常见操作，对于表春queue操作，offer，poll，peek都不会抛出异常，只会返回false或null，而add，remove，element这些操作则会在不满足条件时抛出异常
     */
    @Test
    public void queue(){
        //基于链表实现的队列没有长度限制
        Queue<String> queue = new LinkedList<>();
        //在有长度限制的队列里，offer会返回异常，offer会返回false，Linkedlist对offer和add的实现完全相同
        queue.offer("A");
        queue.add("B");
        System.out.println(queue);
        //删除第一个元素并获取删除的值，在队列为空时，remove会抛出异常，poll会返回null
//        queue.poll();
//        queue.remove();
        //不删除返回队头元素，peek会返回null，element会抛出no such element异常
        queue.peek();
        queue.element();

        //在队头添加
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("A");
        deque.offerFirst("A");
        //在队列尾部添加
        deque.addLast("B");
        deque.offerLast("B");
        deque.add("B");
        deque.offer("B");
        //删除类似
    }

    /**
     * stack是线程安全的，JDk推荐使用Deque来取代Stack实现LIFO的数据结构
     */
    @Test
    public void stack(){
        Stack<String> stack = new Stack<>();
        stack.push("S");
        //没有区别
        stack.empty();
        stack.isEmpty();
        stack.pop();
        stack.peek();

        Deque<String> deque = new LinkedList<>();
        deque.addFirst("A");
        deque.pollFirst();
    }
}