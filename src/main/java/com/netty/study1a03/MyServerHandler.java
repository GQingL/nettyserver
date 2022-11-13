package com.netty.study1a03;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author：liangguangqing
 * @date：2022/11/13 13:31
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    public void channelActive(ChannelHandlerContext ctx){
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("端口：" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+ "接收到信息" + msg);
    }
}
