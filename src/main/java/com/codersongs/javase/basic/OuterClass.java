package com.codersongs.javase.basic;

import com.codersongs.javase.base.Student;

public class OuterClass {
    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass(2);
        InnerClass.Draw draw = innerClass.new Draw(3);
        draw.drawSahpe();
        draw.getRadis();
//        可以直接访问
        System.out.println(InnerClass.StaticInner.fieldA);;
        //静态内部类的实例化方式
        InnerClass.StaticInner staticInner = new InnerClass.StaticInner();
        staticInner.getOuterField();

        innerClass.partialClass("Hello", 3);
    }
}
