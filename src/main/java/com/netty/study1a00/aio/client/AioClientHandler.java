package com.netty.study1a00.aio.client;

import com.netty.study1a00.aio.ChannelAdapter;
import com.netty.study1a00.aio.ChannelHandler;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author：liangguangqing
 * @date：2022/11/12 21:52
 */
public class AioClientHandler extends ChannelAdapter {

    public AioClientHandler(AsynchronousSocketChannel channel, Charset charset) throws IOException {
        super(channel, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx){
        try {
            System.out.println("链接报告信息:" + ctx.channel().getRemoteAddress());
            //通知客户端链接建立成功
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void channelInactive(ChannelHandler ctx){

    }

    public void channelRead(ChannelHandler ctx,Object msg){
        System.out.println("服务端收到:" + new Date() + " " + msg + "\r\n");
        ctx.writeAndFlush("客户端信息处理success! \r\n");
    }
}
