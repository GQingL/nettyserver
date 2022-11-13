package com.netty.study1a00.aio;

import com.netty.study1a00.aio.serve.AioServer;

import java.io.IOException;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @author：liangguangqing
 * @date：2022/11/12 22:23
 */
public abstract class ChannelInitializer implements CompletionHandler<AsynchronousSocketChannel, AioServer> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AioServer attachment) {

        try {
            initChannel(channel);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            attachment.serverSocketChannel().accept(attachment,this);
        }
    }

    @Override
    public void failed(Throwable exc, AioServer attachment) {
        exc.getStackTrace();
    }

    protected abstract void initChannel(AsynchronousSocketChannel channel) throws IOException;
}
