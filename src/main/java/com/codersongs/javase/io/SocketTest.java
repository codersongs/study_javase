package com.codersongs.javase.io;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;

public class SocketTest {
    @Test
    public void chatServer(){
        int port=4406;
        try {
            //创建服务端
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("服务端启动，运行在"+serverSocket.getLocalSocketAddress());
            //等待客户端连接
            Socket clientSocket=serverSocket.accept();//此时阻塞，等待客户端连接  直到客户端连接服务端返回Socket
            System.out.println("有新用户连接，客户名为"+clientSocket.getPort());
            //数据接收和发送
            //1.接收
            InputStream in = clientSocket.getInputStream();
            Scanner scanner = new Scanner(in);

            System.out.println("客户端请求参数：" + scanner.nextLine());
            //2.发送
            OutputStream out = clientSocket.getOutputStream();
            PrintStream printStream = new PrintStream(out);
            printStream.println("服务端响应参数");
            printStream.println("服务端响应参数2");
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void chatClient(){
        String ip="127.0.0.1";
        int port=4406;
        try {
            Socket socket=new Socket(ip,port);
            //发送消息
            OutputStream out = socket.getOutputStream();
            PrintStream printStream=new PrintStream(out);
            printStream.println("客户端请求：嗨，你好");
            printStream.println("客户端请求：嗨，你好2");
            printStream.flush();
            //接收消息
            InputStream in = socket.getInputStream();
            Scanner scanner = new Scanner(in);
            System.out.println("服务端响应：" + scanner.nextLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * bw的readLine和scanner的nextLine都是阻塞的，服务端要实现交互功能必须要用请求结束标识符
     * @throws Exception
     */
    @Test
    public void bioServer() throws Exception{
        int port = 4343; //端口号

        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // 等待连接
            System.out.println("====================================");
            Socket socket = serverSocket.accept();
            //获取客户端请求数据
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            //对请求数据的读取也是阻塞的
            while ((line = bufferedReader.readLine()) != null){
                if ("FINISH".equalsIgnoreCase(line)){
                    break;
                }
                System.out.println("客户端请求:" + line);
            }
            //构造响应数据，这个过程中是阻塞的
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            printWriter.println("This is my response: hello world！");
            printWriter.println("FINISH");
            printWriter.flush();
            while ((line = bufferedReader.readLine()) != null){
                if ("FINISH".equalsIgnoreCase(line)){
                    break;
                }
            }
            printWriter.close();
            bufferedReader.close();
        }
    }
    @Test
    public void bioClient1() throws Exception{
        System.out.println("====================================");
        //客户端建立连接
        Socket socket = new Socket(InetAddress.getLocalHost(), 4343);
        //客户端请求数据
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("client request data1");
        printWriter.println("FINISH");
        printWriter.flush();

        //客户端获取响应数据，只有读取结束后才能进行其他操作，这就是同步
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            if ("FINISH".equalsIgnoreCase(line)){
                printWriter.write("FINISH");
                break;
            }
        }
        printWriter.close();
        bufferedReader.close();
        socket.close();

    }
    @Test
    public void bioClient2() throws Exception{
        System.out.println("====================================");
        //客户端建立连接
        Socket socket = new Socket(InetAddress.getLocalHost(), 4343);
        //客户端请求数据
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
        printWriter.println("client request data2");
        printWriter.println("FINISH");
        printWriter.flush();

        //客户端获取响应数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while ((line = bufferedReader.readLine()) != null){
            System.out.println(line);
            if ("FINISH".equalsIgnoreCase(line)){
                printWriter.write("FINISH");
                break;
            }
        }
        printWriter.close();
        bufferedReader.close();
        socket.close();

    }

    /**
     * IO面向Stream，nio面向Buffer
     * IO多路复用，搭配非阻塞socket，避免读取不完整的数据
     *
     * IO多路复用为什么会使用非阻塞IO
     * 多进程同时对某个socket进行监听，当新的连接完成3次握手后，进程均被select,epoll唤醒，但是最后只有1个进程可以accept，没能accept的进程被block
     * 某个socket接收缓冲区有新数据分节到达，然后select报告这个socket描述符可读，但随后，协议栈检查到这个新分节检验和错误，然后丢弃这个分节，这时候调用read则无数据可读
     * 边缘触发环境，由于无法知道多少数据可读，所以accept1次后，第二次尝试accept可能会被阻塞，此时应该使用非阻塞IO
     *
     * @throws Exception
     */
    @Test
    public void nio() throws Exception{
        // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
        // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
        //两个selector没有本质的区别，都是用来检测是否有数据可用
        Selector serverSelector = Selector.open();
        // 2. clientSelector负责轮询连接是否有数据可读，所不同的是用于客户端注册还是服务端注册
        Selector clientSelector = Selector.open();
        new Thread(() -> {
            try {
                // 对应IO编程中服务端启动
                ServerSocketChannel listenerChannel = ServerSocketChannel.open();
                //绑定端口
                listenerChannel.socket().bind(new InetSocketAddress(3333));
                //非阻塞
                listenerChannel.configureBlocking(false);
                //注册到selector
                listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
                while (true) {
                    // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                    if (serverSelector.select(1) > 0) {
                        Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable()) {
                                try {
                                    // 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                                    clientChannel.configureBlocking(false);
                                    clientChannel.register(clientSelector, SelectionKey.OP_READ);
                                } finally {
                                    keyIterator.remove();
                                }
                            }
                        }
                    }
                }
            } catch (IOException ignored) {
                    ignored.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            try {
                while (true) {
                    // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
                    if (clientSelector.select(1) > 0) {
                        Set<SelectionKey> set = clientSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isReadable()) {
                                try {
                                    SocketChannel clientChannel = (SocketChannel) key.channel();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    // (3) 面向 Buffer
                                    clientChannel.read(byteBuffer);
                                    byteBuffer.flip();
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
                                } finally {
                                    keyIterator.remove();
                                    key.interestOps(SelectionKey.OP_READ);
                                }
                            }
                        }
                    }
                }
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
        }).start();
    }


    @Test
    public void aio() throws Exception{
        int port = 7894;
        // AIO线程复用版
        Thread sThread = new Thread(new Runnable() {
            @Override
            public void run() {
                AsynchronousChannelGroup group = null;
                try {
                    group = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
                    //开启server
                    AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(InetAddress.getLocalHost(), port));
                    server.accept(null, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                        //回调
                        @Override
                        public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {
                            server.accept(null, this); // 接收下一个请求
                            try {
                                Future<Integer> f = result.write(Charset.defaultCharset().encode("你好，世界"));
                                f.get();
                                System.out.println("服务端发送时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                                result.close();
                            } catch (InterruptedException | ExecutionException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                        @Override
                        public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {
                        }
                    });
                    group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        sThread.start();

        // Socket 客户端
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        Future<Void> future = client.connect(new InetSocketAddress(InetAddress.getLocalHost(), port));
        future.get();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        client.read(buffer, null, new CompletionHandler<Integer, Void>() {
            @Override
            public void completed(Integer result, Void attachment) {
                System.out.println("客户端打印：" + new String(buffer.array()));
            }

            @Override
            public void failed(Throwable exc, Void attachment) {
                exc.printStackTrace();
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread.sleep(10 * 1000);
    }
}
