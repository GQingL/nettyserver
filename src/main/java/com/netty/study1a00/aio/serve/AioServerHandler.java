package com.netty.study1a00.aio.serve;

import com.netty.study1a00.aio.ChannelAdapter;
import com.netty.study1a00.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author：liangguangqing
 * @date：2022/11/12 22:33
 */
public class AioServerHandler extends ChannelAdapter {

    public AioServerHandler(AsynchronousSocketChannel channel, Charset charset) throws IOException {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx){
        try {
            System.out.println("连接报告信息:" + ctx.channel().getRemoteAddress());
            ctx.writeAndFlush("通知服务端链接建立成功" + " " + new Date() + " " + ctx.channel().getRemoteAddress() + "\r\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void channelInactive(ChannelHandler ctx){

    }

    public void channelRead(ChannelHandler ctx,Object msg){
        System.out.println("服务端收到：" + new Date() + " " + msg + "\r\n");
        ctx.writeAndFlush("服务端信息处理success！ \r\n");
    }

}
