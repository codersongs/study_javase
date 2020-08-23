package com.codersongs.javase.basic;

import com.codersongs.javase.base.Student;

public class InnerClass {
    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        Draw draw = innerClass.new Draw();
    }
    private double radius = 0;
    public static int count =1;

    public InnerClass() {
    }

    public void outerMethod(){

    }
    public InnerClass(double radius) {
        this.radius = radius;
    }
    //成员内部类支持四种访问权限，外部类只支持public和default两种
    //成员内部类相对于一个实例变量，由于需要先对外部类进行初始化才能初始化内部类，显然成员内部类不支持声明任务static相关的属性，方法和代码块
    class Draw {     //内部类
        private int name;
        private int radius;
//        private static int name1;//不能声明静态属性
//        static {}//不能舍命静态代码块

        public Draw() {
        }

        public Draw(int radius) {
            this.radius = radius;
        }

        public void drawSahpe() {
            System.out.println(radius);  //外部类的private成员
            System.out.println(count);   //外部类的静态成员
        }
        //默认使用内部的radius
        public void getRadis(){
            System.out.println(radius);
            System.out.println(InnerClass.this.radius);//使用外部类.this.属性/方法，访问外部类的属性或方法
        }

        //不能声明静态方法
//        public static void staticMethod(){
//
//        }
    }
    //静态内部类相当于一个静态属性，不能访问外部类非静态的属性和方法
    static class StaticInner{
        public static final String fieldA = "Hello";
        public StaticInner() {
        }
        static {

        }
        public void getOuterField(){
//            outerMethod();
//            radius;//无法访问外部类的非静态属性或方法
            System.out.println(count);
            System.out.println(Student.flag);
        }
    }
    //方法内部类相当于一个局部变量，不能有访问修饰符，只要方法内部能使用
    public void partialClass(String a, int b){
        class Primary{
            public static final String f1 = "PRIMAY";
            public int qq;

            public void methodA(){

            }
            public String methodB(){
                System.out.println(a);
//                a = 3;//不能修改a，a应该作为final 传入
                return null;
            }
        }
        /**
         * 这个地方很奇怪，JDK1.8以后，局部变量或入参不需要声明为final但是编译时会作为final属性进行编译，因此在修改时会报错
         * 背后的原理是，内部类中如果开启线程，有可能会导致内部类的声明周期比方法更长，会导致方法执行完成后实际上内部类扔在使用，
         * 变量的声明周期会随着方法的出栈结束，因此会导致变量在方法内部类中无法访问。final其实并不能延长声明周期，为了解决这个
         * 问题，编译器在编译时会将局部变量复制一份传给方法内部类，这样原变量过期后，方法内部类中的变量依然可以被使用，但是这也
         * 会有一个问题，由于变量是复制的，相当于浅拷贝，如果方法中修改了局部变量，在内部类中是不可用的，这样会导致代码逻辑的混乱
         * 和结果的不符合预期，因此，为了避免这个混淆，Java规定方法内部类使用的外部参数必须是final修饰
         */
//        a = 6;

        Primary primary = new Primary();
        primary.methodB();

    }

    /**
     * 匿名内部类就是没有名称的方法内部类
     * 匿名内部类必须实现一个接口或继承一个抽象类
     * 匿名内部类没有名称，没有构造器
     */
    public void anonymouseInnerClass(){
        Runnable runnable = new Runnable(){
            @Override
            public void run(){
                System.out.println("inner");
            }
        };
        Thread thread = new Thread();
        thread.start();
    }
}
