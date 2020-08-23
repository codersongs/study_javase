package com.codersongs.javase.basic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Java的泛型是伪泛型，在编译期间，所有的泛型信息都会被擦除，泛型是在编译器这个层次实现的，字节码中不存在泛型信息
 */
public class Generic {
    @Test
    public void testGeneric(){
        List<String> strList = new ArrayList<>();
        List<Integer> intList = new ArrayList<>();

        System.out.println(strList.getClass() == intList.getClass());
    }

    @Test
    public void genericWildCard(){
       List<String> list = new ArrayList<>();
       list.add("A");
       all(list);

        List<GenericParent> parentList = new ArrayList<>();
        extendsObj(parentList);

        List<GenericSon> sonList = new ArrayList<>();
        extendsObj(sonList);

        superObj(sonList);
        superObj(parentList);

//        parentObj(sonList);//编译报错，List<Base>和List<Sbu>不具备父子关系
        parentObj(parentList);
    }


    public void all(List<?> list){
        System.out.println(list);
        list.remove("A");
//        list.add("B");//?通配符不具备添加能力
    }

    public void extendsObj(List<? extends GenericParent> list){
        System.out.println("extendsObj");
    }

    public void superObj(List<? super GenericSon> list){
        System.out.println("superObj");
    }

    public void parentObj(List<GenericParent> list){
        System.out.println("parentObj");
    }
}
class GenericParent{
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}

class GenericSon extends GenericParent{
    private String emai;

    public GenericSon(String emai) {
        this.emai = emai;
    }
}
