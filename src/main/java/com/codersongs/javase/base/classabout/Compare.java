package com.codersongs.javase.base.classabout;

import org.junit.Test;

import java.util.*;

public class Compare {
    @Test
    public void testComparable(){
        //List
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(24, "A"));
        personList.add(new Person(18, "B"));
        personList.add(new Person(32, "C"));
        //用于sort
        Collections.sort(personList);
        System.out.println(personList);

        Map<Person, String> treeMap = new TreeMap<>();
        //用于treemap
        treeMap.put(new Person(32, "A"), "1");
        treeMap.put(new Person(23, "B"), "2");
        treeMap.put(new Person(72, "C"), "3");
        System.out.println(treeMap);
    }

    @Test
    public void testComparator(){
        //List
        List<Person> personList = new ArrayList<>();
        personList.add(new Person(24, "A"));
        personList.add(new Person(18, "B"));
        personList.add(new Person(32, "C"));
        //用于sort，定制排序的优先级高于自然排序
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        System.out.println(personList);

        Map<Person, String> treeMap = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o2.getAge() - o1.getAge();
            }
        });
        //用于treemap
        treeMap.put(new Person(32, "A"), "1");
        treeMap.put(new Person(23, "B"), "2");
        treeMap.put(new Person(72, "C"), "3");
        System.out.println(treeMap);
    }
}

//自然排序，只能实现一种排序方式
class Person implements Comparable<Person>{
    private int age;
    private String name;

    public Person() {
    }

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    /**
     * 按照年龄升序排列
     * @param o
     * @return
     */
    @Override
    public int compareTo(Person o) {

        return this.getAge() - o.getAge();
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}