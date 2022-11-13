package com.netty.study1a00.aio.serve;

import com.netty.study1a00.aio.ChannelInitializer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author：liangguangqing
 * @date：2022/11/12 22:30
 */
public class AioServerChannelInitializer extends ChannelInitializer {

    protected void initChannel(AsynchronousSocketChannel channel) throws IOException {
        channel.read(ByteBuffer.allocate(1024),10, TimeUnit.SECONDS,
                null,new AioServerHandler(channel, Charset.forName("GBK")));
    }
}
