package com.codersongs.javase.collection;

import org.junit.Test;

import java.util.*;

public class MapAbout {
    /**
     * 经典数据结构HashMap，核心点：
     * 1.数据结构：数组 + 链表/红黑树，链表长度 >= 8 转换为红黑树，小于6时转换为链表
     * 2.长度：
     *     ①.无参构造器: 初始化HashMap不会初始化数组，第一次添加元素时初始化为16，之后每次double
     *     ②.初始化长度：如果不是2的幂次方，会调整为大于该值的最小的2的幂次方，原因是hash & (length - 1) = hash mod length，计算更加高效
     * 3.哈希函数：(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16)，高位参与运算，避免了与运算只与低位有关的局限性，减少哈希碰撞的可能
     * 4.扩容：double，触发条件：元素数量 > 数组长度 * 负载因子；数组长度小于64，单个桶链表长度 > 8
     * 5.负载因子：默认0.75，元素数量 > 长度 * 0.75时进行扩容，越大的负载因子，数组的利用率越高，空间占用更小，越小的负载因子，数组使用率越低，但是哈希碰撞的概率越低，本质上是一个时间和空间的权衡
     * 6.树形化条件：链表长度at least 8，数组长度大于64，对于过小的数组树形化没有意义，会进行扩容操作
     * 7.空键值：null key只有一个，hashcode 为0，会覆盖，null value不做限制
     * 8.如何定位一个元素：hash % length；==比较，equals比较
     * 9.遍历时删除，会快速时报，抛出ConcurrentModificationException，避免读到脏数据，遍历时使用HashMap$HashIterator
     * 10.HashMap死链问题：死循环是并发写操作导致的
     */
    @Test
    public void hashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("A", "A");
        map.put(null, "nullValue");
        System.out.println(map.get(null));
        map.put(null, "nullValue2");
        System.out.println(map.get(null));
        //三种遍历方式，获取keySet，获取value collection，获取entrySet
        Collection<String> values = map.values();
        Set<String> strings = map.keySet();
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println("iter:" + entry.getKey());
//            map.remove(entry.getKey()); //快速失败，遍历的时候被修改，遍历到的数据是不可靠的
        }

    }

    /**
     * HashSet本质上是一个value为PRESENT = new Object的HashMap
     */
    @Test
    public void hashSet(){
        Set<String> set = new HashSet<>();
        set.add("ABC");
    }

    /**
     * HashTable是线程安全版的HashMap，继承自Dictionary，实现了Map接口
     * 长度：默认11，扩容为double + 1
     * 负载因子默认0.75f
     * hashtable的key和value都不能为null，会直接抛出异常
     * 哈希方法:int hash = key.hashCode(); int index = (hash & 0x7FFFFFFF) % tab.length;
     * HashMap没有contains，而hashtable有contains containsKey和containsValue，contains和containsValue完全相同
     * 遍历使用 Enumerator
     */
    @Test
    public void hashTable(){
        Hashtable<String, String> table = new Hashtable<>();
        table.put("A", "1");
        table.put("B", "2");
        table.put("C", "3");
        System.out.println(table);
        Set<Map.Entry<String, String>> entries = table.entrySet();
        table.contains("A");
        table.containsValue("B");

        for (Map.Entry<String, String> entry : entries) {
            table.remove(entry.getKey());
        }
    }

    /**
     * linkedHashMap是HashMap + 带头尾指针的双向链表，解决的是HashMap的无序问题
     * afterInsertion方法可以根据需要删除最近最久未使用数据，实现LRU功能
     */
    @Test
    public void linkedHashMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("A", "1");
        map.put("B", "2");
        map.put("C", "3");
        System.out.println(map);
    }

    /**
     * TreeMap是一个排序Map，底层实现为红黑树，根据Key排序，可以不能为null，因为无法为一个null key执行compareTo操作
     */
    @Test
    public void treeMap(){
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("B", 3);
        treeMap.put("C", 1);
        treeMap.put("A", 2);
        System.out.println(treeMap);
    }
}
