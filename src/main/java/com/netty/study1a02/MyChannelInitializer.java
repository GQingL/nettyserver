package com.netty.study1a02;


import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * @author：liangguangqing
 * @date：2022/11/13 12:34
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) {
        System.out.println("链接报告开始");
        System.out.println("链接报告信息: 有一客户端链接到本地服务端");
        System.out.println("链接报告IP:"+channel.localAddress().getHostString());
        System.out.println("链接报告port："+channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //在管道中添加我们自己的接收数据实现方式
        channel.pipeline().addLast(new MyServerHandler());
    }
}
