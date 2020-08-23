package com.codersongs.javase.basic;

import java.io.IOException;

public class OverrideClass extends Parent{
//    @Override
//    public String methodA() {
//        return null;
//    }

    //可以不抛出异常或者抛出不大于被重写方法的异常
//    @Override
//    public String methodA() throws SocketException {
//        return null;
//    }
    //编译不通过
//    @Override
//    public String methodA() throws Exception {
//        return null;
//    }
//    @Override
//    public String methodA() throws SocketException {
//        return null;
//    }

    //访问修饰符不能小于父类
//    @Override
//    private String methodA() throws SocketException {
//        return null;
//    }
}
class Parent{
    protected String methodA()throws IOException {
        return null;
    }
}