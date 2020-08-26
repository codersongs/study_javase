package com.codersongs.javase.io;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Java默认使用的UTF16，Unicode编码有两个版本，第一个版本中UTF16使用两个字节，UTF8是不定长的，以开头的1的数量标志字节数，10表示非开头
 * 第二个版本UTF32固定为4个字节，UTF8不定长，UTF16为两字节或者4字节
 */
public class Encode {
    @Test
    public void encodeLen() throws UnsupportedEncodingException {
//        String english = "A";
//        byte[] asciis = english.getBytes("ASCII");
//        //ASCII编码只有一个字节，且最高位不用，因此只能支持128个字符
//        System.out.println("ascii len:" + asciis.length + "," + Arrays.toString(asciis));
//        byte[] gbks = "中".getBytes("GBK");
//        //GB2312是一种中文编码集，GBK是GB2312的扩展
//        System.out.println(gbks.length + "," + Arrays.toString(gbks));
//
//        byte[] utf16 = english.getBytes("UTF-16");
//        System.out.println(utf16.length);
//        byte[] utf8 = english.getBytes("UTF-8");
//        System.out.println(utf8.length);
        System.out.println("中".getBytes("UTF-16").length);
        System.out.println("中".getBytes("UTF-8").length);
        System.out.println("中".getBytes("GB2312").length);
        System.out.println("中".getBytes("GBK").length);
    }

    @Test
    public void testHan() throws UnsupportedEncodingException {
        //01101100 01001001
        String binary = Integer.toBinaryString(27721);
        System.out.println(binary);
        System.out.println(convertBytes2Binary("汉".getBytes(StandardCharsets.UTF_8)));
        System.out.println(convertBytes2Binary("汉".getBytes(StandardCharsets.UTF_16)));
        System.out.println(convertBytes2Binary("汉".getBytes("UTF-32")));
    }

    @Test
    public void testChar(){

    }

    public String convertBytes2Binary(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for (byte aByte : bytes) {
            int a = (int) aByte;
            String s = Integer.toBinaryString(a);
//            if (s.length() > 8){
//                s = s.substring(s.length()-8);
//            }
            builder.append(s).append(",");
        }
        return builder.substring(0, builder.length()-1);
    }
}
