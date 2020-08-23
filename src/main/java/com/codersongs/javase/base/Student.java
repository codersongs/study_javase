package com.codersongs.javase.base;

public class Student {
    public static String flag = "STUDENT";
    public String email;
    private int id;
    private String name = "Alice";

    public Student() {
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String methodA(){
        System.out.println("execute methodA");
        return "hello world";
    }

    private void privateMethod(){
        System.out.println("pirvateMethod");
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
