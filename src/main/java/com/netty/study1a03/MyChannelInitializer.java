package com.netty.study1a03;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 13:25
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel channel){
        /**
         * 解码器
         */
        // 基于换行符号
        //channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
        // 基于指定字符串【换行符，这样功能等同于LineBasedFrameDecoder】
        //channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,false, Delimiters.lineDelimiter()));
        // 基于最大长度
        channel.pipeline().addLast(new FixedLengthFrameDecoder(4));
        // 解码转string，注意调整自己的编码格式gbk utf-8
        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
        // 在管道中添加我们自己的接收数据的实现方式
        channel.pipeline().addLast(new MyServerHandler());
    }
}
