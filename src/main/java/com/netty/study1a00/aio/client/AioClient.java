package com.netty.study1a00.aio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author：liangguangqing
 * @date：2022/11/12 21:46
 */
public class AioClient {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open();
        Future<Void> connect = socketChannel.connect(new InetSocketAddress("192.168.0.104", 7397));
        connect.get();
        socketChannel.read(ByteBuffer.allocate(1024),null,
                new AioClientHandler(socketChannel, Charset.forName("GBK")));
        Thread.sleep(100000);
    }
}
