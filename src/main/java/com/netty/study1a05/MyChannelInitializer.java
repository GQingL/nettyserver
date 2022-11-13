package com.netty.study1a05;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 17:11
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        // 基于换行符号
        channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 编码转string 注意自己编码格式为gbk还是utf-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 解码转string
        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
        // 在管道中添加我们自己接收数据的实现方式
        channel.pipeline().addLast(new MyServerHandler());
    }
}
