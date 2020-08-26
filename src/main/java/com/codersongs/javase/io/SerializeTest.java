package com.codersongs.javase.io;

import org.junit.Test;

import java.io.*;

/**
 * 对于序列化，最核心的一个点就是序列号ID
 */
public class SerializeTest implements Serializable {
    //idea默认生成版本号
    private static final long serialVersionUID = -5420865171632975489L;

    public static void main(String[] args) throws Exception {
        String path = "/Users/song/Desktop/test/javase/serilize.txt";
        SerializeTest serializeTest = new SerializeTest();
//        serializeTest.setAge(12);
//        serializeTest.setName("Alice");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
        oos.writeObject(serializeTest);

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
        SerializeTest serializeTestois = (SerializeTest) ois.readObject();
        System.out.println(serializeTestois);
    }

    /**
     * 序列化的对象只能有一个构造器
     * @throws Exception
     */
    @Test
    public void serialize() throws  Exception{
        String path = "/Users/song/Desktop/test/javase/serilize.txt";
        SerializeTest serializeTest = new SerializeTest();
        serializeTest.setAge(12);
        serializeTest.setName("Alice");
        serializeTest.staticField = "ABCD";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(path)));
        oos.writeObject(serializeTest);
    }

    /**
     * 在指定序列号的前提下
     *  1.反序列化时增加一个属性是可以反序列化的，
     *  2.删除一个属性也只会导致反序列化后属性减少一个
     *  3.修改一个属性的类型会导致java.io.InvalidClassException
     *  4.java.io.InvalidClassException，修改序列化版本号会导致反序列化失败
     * 在不指定序列号的前提下
     *  1.JVM会根据属性默认生成一个序列号，不同的JVM生成的序列号也可能不一样，这个时候如果对属性进行CRUD的话，会改变序列化版本号，导致反序列化的失败
     *
     * @throws Exception
     */
    @Test
    public void deserialize() throws Exception{
        String path = "/Users/song/Desktop/test/javase/serilize.txt";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(path)));
        SerializeTest serializeTest = (SerializeTest) ois.readObject();
        System.out.println(serializeTest);
        System.out.println(serializeTest.staticField);
    }
//    private static final long serialVersionUID =2L;
    //被transient修饰的变量不会被序列化
    private transient String  name;
    private int age;
    private int addField;
    //静态变量不能被初始化
    public static String staticField;
    //覆盖默认的序列化和反序列化行为
//    private void writeObject(ObjectOutputStream oos) throws IOException {
//        // oos.defaultWriteObject();
//        oos.writeObject(name);
//        oos.writeObject(staticField);
//    }
//
//    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
//        // ois.defaultReadObject();
//        name = (String) ois.readObject();
//        staticField = (String) ois.readObject();
//    }
//    public static long getSerialVersionUID() {
//        return serialVersionUID;
//    }

//    public int getName() {
//        return name;
//    }
//
//    public void setName(int name) {
//        this.name = name;
//    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SerializeTest() {
    }

    public int getAddField() {
        return addField;
    }

    public void setAddField(int addField) {
        this.addField = addField;
    }
    //    public SerializeTest(String name, int age) {
//        this.name = name;
//        this.age = age;
//    }

    @Override
    public String toString() {
        return "SerializeTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
