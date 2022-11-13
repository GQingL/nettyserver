package com.netty.study1a00.bio.serve;

import com.netty.study1a00.bio.ChannelAdapter;
import com.netty.study1a00.bio.ChannelHandler;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：liangguangqing
 * @date：2022/11/13 01:02
 */
public class BioServerHandler extends ChannelAdapter {

    public BioServerHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(ChannelHandler ctx) {
        System.out.println("俩捏报告LocalAddress："+ctx.socket().getLocalAddress());
        ctx.writeAndFlush("bioservice to msg for you \r\n");
    }

    @Override
    public void channelRead(ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        ctx.writeAndFlush("hi 我已经收到你的消息success \r\n");
    }
}
