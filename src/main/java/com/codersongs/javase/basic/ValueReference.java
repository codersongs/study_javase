package com.codersongs.javase.basic;

import com.codersongs.javase.base.Student;

public class ValueReference {
    public static void main(String[] args) {
        ValueReference valueReference = new ValueReference();
        int a = 1;
        valueReference.test1(a);
        System.out.println(a);
//        Student student = new Student();
//        student.setName("origin");
//        valueReference.test2(student);
//        System.out.println(student);
    }

    //基本数据类型值传递，可以使用wrappe类实现修改
    private void test1(int a){
        a = 2;
    }

    //应用数据类型值传递
    private void test2(Student student){
        student.setName("changed");
    }
}
