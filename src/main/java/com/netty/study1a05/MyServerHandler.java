package com.netty.study1a05;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author：liangguangqing
 * @date：2022/11/13 17:15
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当客户端主动链接，这个通道变为活跃
     */
    public void channelActive(ChannelHandlerContext ctx){
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本地服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        // 通知客户端链接建立成功
        String str = "通知客户端链接建立成功" + new Date() + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 当客户端主动断开链接后，该通道变为不活跃，且不能传输数据
     */
    public void  channelInactive(ChannelHandlerContext ctx){
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg){
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "接收到消息：" + msg);
        // 通知客户端链接消息发送成功
        String str = "服务端收到:" + new Date() + msg + "\r\n";
        ctx.writeAndFlush(str);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable){
        ctx.close();
        System.out.println("异常信息: \r\n" + throwable.getMessage());
    }
}
