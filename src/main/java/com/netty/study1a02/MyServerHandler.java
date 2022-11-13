package com.netty.study1a02;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author：liangguangqing
 * @date：2022/11/13 13:06
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        //接收msg消息
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        System.out.println(new Date() + "接收到消息：");
        System.out.println(new String(bytes, Charset.forName("GBK")));
    }
}
