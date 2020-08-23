package com.codersongs.javase.keyword;

import com.codersongs.javase.base.Student;

/**
 *
 */
//final修饰类，表示不可继承
public final class Final {
    //final修饰的变量不可修改，实例变量可以在定义时，代码块，构造器中赋值
    private final int a;
    //类变量只能在声明时活静态代码块赋值
    private static final String b;
    private final Student student;
    private Final finalRefer;

    static {
        b = "b";
    }

    public Final(int a, int b) {
        this.a = a; //对a的写不能重排序到构造方法之外，初次读该对象的应用与读该引用的final域不能重排序
        this.student = new Student();
        student.setName("name");//对成员域student的写与 Final引用被赋值给引用变量不能重排序
        finalRefer = this;//this 逸出
    }

    //final修饰的方法不能被重写
    public final void methodA(){
        System.out.println("methodA");
    }

    //final 入参不能修改
    public void finalParam(final int a, final  String b){
        //final 局部变量只能被赋值一次
        final int c;
        c = 1;
//        c = 2;//
    }

    public void syncBlock(){
        //finalRefer最好是final的，保证无法被重新赋值，否则的话，引用被修改的话，其他线程能够重新获取锁
        synchronized (finalRefer){

        }
    }


    private void privateFinalMethod(){
        System.out.println("aaa");
        System.out.println("ccc");
    }
}
