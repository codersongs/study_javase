package com.codersongs.javase.datatype;

import org.junit.Test;

/**
 * 字符串是一个字符数组，长度是不可变的
 */
public class StringType {
    @Test
    public void format(){
        System.out.println(String.format("a%s", "hi"));
    }
    @Test
    public void concat(){
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program";
        String s4 = "ming";
        String s5 = "Program" + "ming";
        String s6 = s3 + s4;
        System.out.println(s1 == s2); // false s1是常量池中的引用，s2是指向堆的引用
        System.out.println(s1 == s5); // true 当+号两边都是字符串常量时，则先回寻找字符串常量池中是否存在已经拼接好的字符串。如果不存在，则会在里面创建一个新的，不会在堆中创建新的对象
        System.out.println(s1 == s6); // false 当+号一边存在字符串变量时，则先回寻找字符串常量池中是否存在已经拼接好的字符串。如果不存在，则会在里面创建一个新的，然后在堆中创建一个指向这个常量的对象。
        System.out.println(s1 == s6.intern()); // true s1和s6均为常量池中的地址
        System.out.println(s2 == s2.intern()); // false s2.intern()为常量池地址，s2为堆中地址
    }

    @Test
    public void reverse(){
        StringBuilder builder = new StringBuilder("abcd");
        System.out.println(builder.reverse());
    }

    /**
     * StringBuilder相当于是维护了一个字符数组，初始化长度为16，当长度不足时进行扩容，扩容规则为：int newCapacity = (value.length << 1) + 2;
     *
     */
    @Test
    public void stringBuilder(){
        StringBuilder builder = new StringBuilder();
        builder.append("abcd");
        builder.toString();//toString方法使用new String，返回string builder的一个拷贝
    }

    /**
     * StringBuffer是线程安全的和StringBuilder都继承自AbstractBuilder
     */
    @Test
    public void stringBuffer(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("abcd");
        System.out.println(buffer);
    }

    /**
     * 字符串操作符重载，使用StringBuilder.append
     * 当拼接的字符串中有一个变量时就会使用StringBuilder；
     * 当要拼接的所有字符都是常量时，编译器会进行优化
     */
    @Test
    public void testPlus(){
        String a = "hello";
        String c = a + "world";
        System.out.println(c);
        String d = "java" + "python";
        System.out.println(d);
    }

    @Test
    public void testStr(){
        String str = "a";
        System.out.println(str.intern());
    }
}
