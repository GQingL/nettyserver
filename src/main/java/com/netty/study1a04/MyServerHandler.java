package com.netty.study1a04;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author：liangguangqing
 * @date：2022/11/13 14:22
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 当客户端主动链接服务端的链接后，这个通道是活跃的，也就是服务端和客户端建立了链接可以传输数据
     */

    public void channelActive(ChannelHandlerContext ctx) throws UnsupportedEncodingException {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息: 有一客户端链接到本服务器");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        // 通知客户端链接建立成功
        String str = "通知客户端链接建立成功" + new Date() + channel.localAddress().getHostString() + "\r\n";
        ByteBuf buffer = Unpooled.buffer(str.getBytes().length);
        buffer.writeBytes(str.getBytes("GBK"));
        ctx.writeAndFlush(buffer);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道是不活跃的。也就是说服务端和客户端的链接断开且不可传输数据
     * @param ctx
     */
    public void channelInactive(ChannelHandlerContext ctx){
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
    }

    public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()) + "接收到消息" + msg);
        // 通知客户端消息发生成功
        String str = "服务端收到：" + new Date() + msg ;
        ByteBuf buffer = Unpooled.buffer(str.getBytes().length);
        buffer.writeBytes(str.getBytes("GBK"));
        ctx.writeAndFlush(buffer);
    }

    /**
     * 抓住异常，当发生异常时，可以做一些相应的处理，比如打印日志，关闭链接
     */

    public void exceptionCaught(ChannelHandlerContext ctx,Throwable throwable){
        ctx.close();
        System.out.println("异常信息：\r\n" + throwable.getMessage());
    }

}

