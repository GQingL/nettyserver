package com.netty.study1a00.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 10:42
 */
public class ChannelHandler {

    private SocketChannel channel;

    private Charset charset;

    public ChannelHandler(SocketChannel channel,Charset charset){
        this.channel = channel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg){
        byte[] bytes = msg.toString().getBytes(charset);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length);
        allocate.put(bytes);
        allocate.flip();
        try {
            channel.write(allocate);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SocketChannel channel(){
        return channel;
    }
}
