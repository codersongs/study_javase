package com.codersongs.javase.keyword;

/**
 * 支持较小的整数类型：byte，short，int及其包装类，字符类型char及其包装类，String，以及枚举
 */
public class Switch {
    public static void main(String[] args) {
        byte a = 1;
        switch (a){
            case 1:
                System.out.println("abc");
                break;
            default:
                break;
        }
        short b = 1;
        switch (b){
            case 1:
                System.out.println("abc");
                break;
            default:
                break;
        }
        int c = 1;
        switch (c){
            case 1:
                System.out.println("abc");
                break;
            default:
                break;
        }
        char d = 'a';
        switch (d){
            case 'd':
                System.out.println("abc");
                break;
            default:
                break;
        }
        String e = "a";
        switch (e){
            case "a":
                System.out.println("abc");
                break;
            default:
                break;
        }
        A f = A.a;
        switch (f){
            case a:
                break;
            default:
                break;
        }
    }
    enum A{
        a;
    }
}
