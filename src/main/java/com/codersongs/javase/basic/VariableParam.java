package com.codersongs.javase.basic;

/**
 * 可变参数
 */
public class VariableParam {
    public static void main(String[] args) {
        VariableParam variableParam = new VariableParam();
        variableParam.methodA(1,2,3);
//        variableParam.mehtodD(new int[]{}, new int[]{});
//        variableParam.mehtodD(new int[]{}, 1,2,3);
        variableParam.methodE(1, 2);
        variableParam.methodG(new int[]{});
//        variableParam.methodG(1,2,3); 数组无法传入可变参数，从入参这个角度来说，可变参数是兼容数组的
        variableParam.methodH();
        variableParam.methodI(1,"a", 2L);
    }

    public void methodA(int... a){
        System.out.println("methodA");
    }

    //编译不通过，可变参数必须放到最后且只能有一个
//    public void methodB(int... a; int b){
//
//    }

//    public void methodC(int... a, int... b){
//
//    }
    //当参数都可行时，程序优先匹配不变参数
    public void methodD(int[] a, int... b){
        System.out.println("methodD");
    }
    //编译不通过，在编译时
//    public void methodD(int[] a, int[] b){
//        System.out.println("method D====");
//    }

    public void methodE(int a){
        System.out.println("method E1");
    }

    public void methodE(int... a){
        System.out.println("method E2");
    }

    public void methodE(int a, int b){
        System.out.println("method E3");
    }

    public void methodF(int... a){
        System.out.println("methodA...");
    }
    //这里要注意，当存在多个参数的时候，可变参数和数组是可以重载的，但是单个参数的时候不可以
//    public void methodF(int[] a){
//        System.out.println("methodF");
//    }

    public void methodG(int[] a){
        System.out.println("methodG");
    }

    public void methodH(int... a){
        System.out.println("methodH");
    }

    public void methodI(Object... args){
        System.out.println("methodI");
    }
}
