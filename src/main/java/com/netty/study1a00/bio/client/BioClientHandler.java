package com.netty.study1a00.bio.client;

import com.netty.study1a00.bio.ChannelAdapter;

import java.net.Socket;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author：liangguangqing
 * @date：2022/11/13 00:31
 */
public class BioClientHandler extends ChannelAdapter {


    public BioClientHandler(Socket socket, Charset charset) {
        super(socket, charset);
    }

    @Override
    public void channelActive(com.netty.study1a00.bio.ChannelHandler ctx) {
        System.out.println("链接报告LocalAddress:" + ctx.socket().getLocalAddress());
        ctx.writeAndFlush("bioclient to msg for you \r\n");
    }

    @Override
    public void channelRead(com.netty.study1a00.bio.ChannelHandler ctx, Object msg) {
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date()) + "收到消息" + msg);
        ctx.writeAndFlush("hi 我已经收到你的消息Success！ \r\n");
    }
}
