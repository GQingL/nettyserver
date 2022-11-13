package com.netty.study1a00.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author：liangguangqing
 * @date：2022/11/13 10:41
 */
public abstract class ChannelAdapter extends Thread{

    private Selector selector;

    private ChannelHandler channelHandler;

    private Charset charset;

    public ChannelAdapter(Selector selector,Charset charset){
        this.selector = selector;
        this.charset = charset;
    }

    public void run() {
        while (true){
            try {
                selector.select(1000);
            } catch (Exception ignore) {
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            SelectionKey key = null;
            while (iterator.hasNext()){
                key = iterator.next();
                iterator.remove();
                try {
                    handleInput(key);
                } catch (Exception ignore) {
                }
            }
        }
    }

    private void handleInput(SelectionKey key) throws IOException {
        if (!key.isValid()) return;

        // 客户端SocketChannel
        Class<?> superclass = key.channel().getClass().getSuperclass();
        if (superclass == SocketChannel.class){
          SocketChannel socketChannel = (SocketChannel) key.channel();
          if (key.isConnectable()){
              if (socketChannel.finishConnect()){
                  channelHandler = new ChannelHandler(socketChannel,charset);
                  channelActive(channelHandler);
                  socketChannel.register(selector,SelectionKey.OP_READ);
              }else {
                  System.exit(1);
              }
          }
        }

        // 服务端serversokcketchannnel
        if (superclass == ServerSocketChannel.class){
            if (key.isAcceptable()){
                ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
                SocketChannel accept = socketChannel.accept();
                accept.configureBlocking(false);
                accept.register(selector,SelectionKey.OP_READ);

                channelHandler = new ChannelHandler(accept, charset);
                channelActive(channelHandler);
            }
        }

        if (key.isReadable()){
           SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            int readBytes = socketChannel.read(allocate);
            if (readBytes > 0){
                allocate.flip();
                byte[] bytes = new byte[allocate.remaining()];
                allocate.get(bytes);
                channelRead(channelHandler,new String(bytes,charset));
            }else if (readBytes < 0){
                key.cancel();
                socketChannel.close();
            }
        }
    }


    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelRead(ChannelHandler ctx,Object msg);
}
