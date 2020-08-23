package com.codersongs.javase.datatype;

/**
 * 基本数据类型
 */
public class BasicType {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);

        Float c = 1.0F;
        Float d = 1.0F;
        System.out.println(c == d);

        short s1 = 1;
//        s1 = s1 + 1;//编译错误，1是int类型，需要进行强制类型转换
        short s2 = 1; s2 += 1; //编译正确，隐含强制类型转换(short)(s1 + 1)

        Integer e = 1;
        Integer f = 1;
        System.out.println(a == b); //true，对于-128 到 127之前的数字jdk会进行缓存，可以通过 -XX:AutoBoxCacheMax=1000或java.lang.Integer.IntegerCache.high=1000来设置

        Integer g = 128;
        Integer h = 128;
        System.out.println(g == h); //false，超过了装箱的缓存范围
    }
}
