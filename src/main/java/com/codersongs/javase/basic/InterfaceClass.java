package com.codersongs.javase.basic;

public interface InterfaceClass {
    //属性一定是public static final的常量
    String s = "aaa";
    //接口不支持构造器
//    public InterfaceClass(){
//
//    }
    //接口的访问修饰符一定是public的
//    protected int methodA();
    abstract int methodB();

    //JDK 1.8以后支持一个默认方法，除此之外的方法都是抽象的
    default int methodC(){
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(InterfaceClass.s);
//        InterfaceClass.s = "b";//final 属性编译报错
    }
}
