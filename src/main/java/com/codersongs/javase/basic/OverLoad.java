package com.codersongs.javase.basic;

public class OverLoad {
    public static void main(String[] args) {

    }


    //overload
    public String overloadA(){
        return null;
    }

    //和修饰符，返回值，是否抛出异常没关系
//    private static final int overloadA() throws Exception{
//        return 0;
//    }
    public String overloadA(int a, int b){
        return null;
    }
    //和参数的类型顺序有关系，和参数名称没关系
//    public String overloadA(int b, int a){
//        return null;
//    }

    public void overloadB(String... a){

    }
    //可变参数，相当于数组
//    public void overloadB(String[] a){
//
//    }

}