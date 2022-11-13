package com.netty.study1a04;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 14:19
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 基于换行符号换行
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 解码转string, 注意调整自己的编码风格GBK,UTF-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 在管道中添加自己的接收数据实现方式
        channel.pipeline().addLast(new MyServerHandler());
    }
}
