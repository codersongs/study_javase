package com.codersongs.javase.io;

import org.junit.Test;

import java.io.*;

/**
 * Java的IO流
 */
public class IOStream {
    private static final String BASE_PATH = "/Users/song/Desktop/test/test_io/";

    /**
     * 字节流
     * @throws Exception
     */
    @Test
    public void inputOutputStream() throws Exception{
        FileInputStream is = new FileInputStream(BASE_PATH + "WechatIMG33.png");
        FileOutputStream os = new FileOutputStream(BASE_PATH + "copy.png");
        byte[] bytes = new byte[1024];
        int length;
        while ((length = is.read(bytes)) > 0){
            os.write(bytes, 0, length);
        }
        is.close();
        os.close();
    }

    /**
     * 处理流，处理流可以提高读写的效率，减少IO次数，设计使用非常经典的装饰者模式
     * @throws Exception
     */
    @Test
    public void bufferdStream() throws Exception{
        InputStream is = new FileInputStream(BASE_PATH + "WechatIMG33.png");
        OutputStream os = new FileOutputStream(BASE_PATH + "copy_buffer.png");
        BufferedInputStream bis = new BufferedInputStream(is);
        BufferedOutputStream bos = new BufferedOutputStream(os);
        byte[] bytes = new byte[1024];
        int len;
        while ((len = bis.read(bytes)) != -1){
            bos.write(bytes, 0, len);
            bos.flush();
        }
        //关闭处理流会关闭文件流
        bis.close();
        bos.close();
    }

    @Test
    public void readerWriter() throws Exception{
        Reader reader = new FileReader(BASE_PATH + "reader.txt");
        BufferedReader br = new BufferedReader(reader);

        FileWriter writer = new FileWriter(BASE_PATH + "writer.txt");
        BufferedWriter bw = new BufferedWriter(writer);
        String line = null;
        while ((line = br.readLine()) != null){
            System.out.println(line);
            bw.write(line);
            bw.newLine();
            bw.flush();
        }
        br.close();
        //只有在调用close或flush的时候才真正写到文件里
        bw.close();
    }

    /**
     * 从字节流到字符流：InputStreamReader、OutputStreamWriter类可以实现
     */
    @Test
    public void byte2Char() throws Exception{
        InputStream is = new FileInputStream(BASE_PATH + "byte2Char.txt");
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        OutputStream os = new FileOutputStream(BASE_PATH + "byte2Char_to.txt");
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);

        String line;
        while ((line = br.readLine()) != null){
            System.out.println(line);
            bw.write(line);
            bw.newLine();
        }
        br.close();
        bw.close();
    }

    /**
     * 从字符流到字节流：可以从字符流中获取char[]数组，转换为String，然后调用String的API函数getBytes() 获取到byte[]，
     * 然后就可以通过ByteArrayInputStream、ByteArrayOutputStream来实现到字节流的转换
     */
    public static void main(String[] args) throws Exception{
        //byte array 内部有一个缓冲数组，可以将其他流中读取的数据写入到缓存中，且不可关闭，定义的是buf的初始化长度，扩容的话每次长度加倍
        ByteArrayOutputStream bOutput = new ByteArrayOutputStream(3);
//        bOutput.close();
        while( bOutput.size()!= 10 ) {
            // 获取用户输入值
            bOutput.write(System.in.read());
        }

        byte b [] = bOutput.toByteArray();
        System.out.println("Print the content");
        for(int x= 0 ; x < b.length; x++) {
            // 打印字符
            System.out.print((char)b[x]  + "   ");
        }
        System.out.println("   ");

        int c;

        //将byte数组读出
        ByteArrayInputStream bInput = new ByteArrayInputStream(b);

        System.out.println("Converting characters to Upper case " );
        for(int y = 0 ; y < 1; y++ ) {
            while(( c= bInput.read())!= -1) {
                System.out.println(Character.toUpperCase((char)c));
            }
            bInput.reset();
        }
    }
    @Test
    public void char2Byte() throws Exception{

    }
}
