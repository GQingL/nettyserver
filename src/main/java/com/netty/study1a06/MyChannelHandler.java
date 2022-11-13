package com.netty.study1a06;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author：liangguangqing
 * @date：2022/11/13 18:40
 */
public class MyChannelHandler extends ChannelInboundHandlerAdapter {

    /**
     * 客户端主动链接，该通道活跃
     */

    public void channelActive(ChannelHandlerContext ctx){
        //当客户端链接后，添加到channelGroup通讯组
        ChannelHandler.channelGroup.add(ctx.channel());
        //日志信息
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接端口：" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        // 通知客户端链接建立成功
        String str = "通知客户端链接建立成功" + new Date() + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(str);
    }

    public void channelInactive(ChannelHandlerContext ctx){
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
        // 当有客户端退出后,从channelGroup中移除
        ChannelHandler.channelGroup.remove(ctx.channel());
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "接收到信息" + msg);
        // 收到信息后，群发给客户端
        String str = "服务端收到：" + new Date() + " " + msg + "\r\n";
        ChannelHandler.channelGroup.writeAndFlush(str);
    }

    /**
     * 抓住异常，当发生异常的时候，可以做一些相应的处理，打印日志 关闭链接
     */

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable){
        ctx.close();
        System.out.println("异常信息：\r\n" + throwable.getMessage());
    }
}
