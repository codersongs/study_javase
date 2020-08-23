package com.codersongs.javase.base.classabout;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * interface和Object是没有关系的，Interface不继承自Object
 */
public class Interface {
    public static void main(String[] args) {
        Set<String> result = new HashSet<>();
//        for (Method m : Set.class.getMethods()){
//            result.add(m.getName());
//        }

//        for (Method m: TestA.class.getMethods()){
//            result.add(m.getName());
//        }
        for (Method m: TestInterface.class.getMethods()){
            result.add(m.getName());
        }
        System.out.println(result);
    }
}
class TestA {
    public void methodA(){

    }
}
