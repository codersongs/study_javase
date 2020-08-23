package com.codersongs.javase.keyword;

import com.codersongs.javase.base.Student;

/**
 * try catch finally执行顺序：
 * 1.正常情况下，try catch finally
 * 2.都有return 的情况下，返回的是finally的return，但是try和catch中的return中的逻辑也会执行
 * 3.finally中没有return，finally在对变量进行操作的时候，会先将变量拷贝一份，将原局部变量PUSH到操作数栈，然后进行操作，最后返回的是拷贝的那份操作数
 * 因此，如果是基本数据类型，finally的修改是不会起作用的，对引用数据类型，只能保证返回的引用不会变，finally本身设计的目的是为了释放资源，因此不会修改局部变量表
 */
public class TryCatchFinally {
    public static void main(String[] args) {
        System.out.println(test1());
        System.out.println(test2());
    }

    /**
     * 基本数据类型
     * @return
     */
    public static int test1(){
        int res = 1;
        try {
            res = 2;
            return res;
        } catch (Exception e){
            e.printStackTrace();

            res = 3;
            return res;
        } finally {
            res = 4;
//            return res;
        }

    }

    /**
     * 引用数据类型
     * @return
     */
    public static Student test2(){
        Student student = new Student();
        try {
            student.setName("a");
            return student;
        }catch (Exception e){
            e.printStackTrace();
            student.setName("b");
            return student;
        }finally {
            student.setName("c");
        }

    }
}
