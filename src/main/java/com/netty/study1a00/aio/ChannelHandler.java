package com.netty.study1a00.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/12 21:58
 */
public class ChannelHandler {

    private AsynchronousSocketChannel channel;

    private Charset charset;

    public ChannelHandler(AsynchronousSocketChannel channel, Charset charset){
        this.channel = channel;
        this.charset = charset;
    }

    public void writeAndFlush(Object msg){
        byte[] bytes = msg.toString().getBytes(charset);
        ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer);
    }

    public AsynchronousSocketChannel channel() {
        return channel;
    }

    public void setChannel(AsynchronousSocketChannel channel) {
        this.channel = channel;
    }
}
