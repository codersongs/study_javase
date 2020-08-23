package com.codersongs.javase.datatype;

public class BoxUnbox {
    private int a;
    public static void main(String[] args) {
        //自动装箱不会产生空指针
        BoxUnbox boxUnbox = new BoxUnbox();
        System.out.println(boxUnbox.a);
        Integer integer = boxUnbox.a;
        System.out.println(integer);
        //自动拆箱会产生空指针
        Integer b = null;
        System.out.println(b.intValue());
    }
}