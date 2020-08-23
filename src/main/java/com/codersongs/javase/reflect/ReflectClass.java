package com.codersongs.javase.reflect;

import com.codersongs.javase.base.Student;
import com.codersongs.javase.basic.InterfaceClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 要理解Class，我们要通过对比通常我们是怎么编译运行一类的，
 * RTTI（Run-Time Type Identification）运行时类型识别，对于这个词一直是 C++ 中的概念，至于Java中出现RRTI的说法则是源于《Thinking in Java》一书，其作用是在运行时识别一个对象的类型和类的信息。这里分两种：
 *
 * 编译期的：也叫传统的”RRTI”，它假定我们在编译期已知道了所有类型(在没有反射机制创建和使用类对象时，一般都是编译期已确定其类型，如new对象时该类必须已定义好)，
 * 另外一种是运行期的：利用反射机制它允许我们在运行时发现和使用类型的信息。
 * 编译时识别一个类，是由程序员来进行的，需要程序员编写好所有类型和相关逻辑，这样编译时就已经知道了所有类型，
 * Class是对Java类的抽象和描述，是Java对象的对象，编写一个Java类A，编译时生成文件A.class，加载到JVM后就是一个Class
 * 什么是Class
 * Class是对象的对象
 * Class是Java类在JVM的保存形式
 *
 * 反射的作用就是可以通过运行时读取JVM的Class信息，动态的去创建实例，为实例赋值，执行实例的方法
 * 例如mysql的驱动类通常写在配置文件里，可以动态加载driver，spring mvc入参将json解析成实体类，由于事先并不知道对象有哪些属性，因此需要反射
 * 动态代理执行方法也是使用的反射，因此反射是设计的基础概念。
 *
 * 不仅仅是Java类，基本数据类型甚至关键字如void也是Class对象
 *
 */
public class ReflectClass {
    public static void main(String[] args) throws Exception{
        //从JVM中加载Student的Class信息
        Class<?> studentClass = Class.forName("com.codersongs.javase.base.Student");
        System.out.println(studentClass);
        Student student = (Student) studentClass.newInstance();
        student.setName("Micheal");
        System.out.println(student);
        Class<? super Student> superclass = Student.class.getSuperclass();
        System.out.println(superclass);
        //通过这段代码可以很容易得出结论，Object是Class的父类，就像其他所有Class一样
        Class<? super Class> superclass1 = Class.class.getSuperclass();
        System.out.println(superclass1);
        //单根继承，Object是没有父类的
        System.out.println(Object.class.getSuperclass());
        //接口和Object没有关系
        System.out.println(InterfaceClass.class.getSuperclass());

//        reflect();
    }

    /**
     * Class.forName，实例getClass，类.class都可以获取Class对象
     * @throws Exception
     */
    public static void get()throws  Exception{
        Class<?> aClass = Class.forName("com.codersongs.javase.base.Student");
        System.out.println(aClass);
        Class<Student> studentClass = Student.class;
//        Student student = new Student();
//        Class<? extends Student> aClass1 = student.getClass();
    }

    @Test
    public void reflectConstructor() throws Exception{
        Class<?> clazz = Class.forName("com.codersongs.javase.base.Student");
        //默认调用无参构造器
        Student student = (Student)clazz.newInstance();
        Constructor<?> constructor = clazz.getConstructor(null);
//        Student student1 = (Student) constructor.newInstance(Integer.class);
        //获取所有共有构造器
        Constructor<?>[] constructors = clazz.getConstructors();
        //所有构造器
        Constructor<?>[] clazzDeclaredConstructors = clazz.getDeclaredConstructors();
    }


    @Test
    public void testReflectField() throws Exception{
        Class<?> clazz = Class.forName("com.codersongs.javase.base.Student");
        Student student = (Student) clazz.newInstance();
        student.setName("Mike");
        Field[] fields = clazz.getFields();
        System.out.println(Arrays.toString(fields));
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField.getName());
        }
        Field nameField = clazz.getDeclaredField("name");
        //私有属性需要设置accessible
        nameField.setAccessible(true);
        Object o = nameField.get(student);
        System.out.println(o);
        nameField.set(student, "Xin");
        Object o1 = nameField.get(student);
        System.out.println(o1);
    }

    @Test
    public void reflectMethod() throws Exception{
        Class<?> clazz = Class.forName("com.codersongs.javase.base.Student");
        Student student = (Student) clazz.newInstance();

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod.getName());
        }

        Method privateMethod = clazz.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(student);
        //根据名称和参数获取方法
        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.invoke(student, "Hello");
        System.out.println(student.getName());
    }
}
