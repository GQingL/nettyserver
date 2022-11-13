package com.netty.study1a06;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 18:33
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转string
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 解码转string
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        // 在管道中添加自己接收数据的实现方式
        channel.pipeline().addLast(new MyChannelHandler());
    }
}
