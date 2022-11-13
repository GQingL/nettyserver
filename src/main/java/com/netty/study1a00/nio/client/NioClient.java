package com.netty.study1a00.nio.client;

import com.netty.study1a00.nio.server.NioServerHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 10:29
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        Selector open = Selector.open();
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);

        boolean connect = socketChannel.connect(new InetSocketAddress("192.168.1.116",7397));
        if (connect){
            socketChannel.register(open, SelectionKey.OP_READ);
        }else {
            socketChannel.register(open,SelectionKey.OP_CONNECT);
        }
        System.out.println("nio client start done");
        new NioClientHandler(open, Charset.forName("GBK")).start();
    }
}
