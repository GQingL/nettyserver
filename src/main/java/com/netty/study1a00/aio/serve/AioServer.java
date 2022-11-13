package com.netty.study1a00.aio.serve;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * @author：liangguangqing
 * @date：2022/11/12 22:25
 */
public class AioServer extends Thread{

    private AsynchronousServerSocketChannel serverSocketChannel;

    public void run(){
        try {
            serverSocketChannel = AsynchronousServerSocketChannel.open(AsynchronousChannelGroup.withCachedThreadPool(Executors.newCachedThreadPool(),10));
            serverSocketChannel.bind(new InetSocketAddress(7397));
            // 等待
            CountDownLatch countDownLatch = new CountDownLatch(1);
            serverSocketChannel.accept(this,new AioServerChannelInitializer());
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public AsynchronousServerSocketChannel serverSocketChannel(){
        return serverSocketChannel;
    }

    public static void main(String[] args) {
        new AioServer().start();
    }

}
