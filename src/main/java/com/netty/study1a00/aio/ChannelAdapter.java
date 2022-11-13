package com.netty.study1a00.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author：liangguangqing
 * @date：2022/11/12 21:53
 */
public abstract class ChannelAdapter implements CompletionHandler<Integer,Object> {

    private AsynchronousSocketChannel channel;

    private Charset charset;

    public ChannelAdapter(AsynchronousSocketChannel channel,Charset charset) throws IOException {
        this.channel = channel;
        this.charset = charset;
        if (channel.isOpen()){
            channelActive(new ChannelHandler(channel,charset));
        }
    }

    @Override
    public void completed(Integer result, Object attachment) {
        try {
            final ByteBuffer buffer = ByteBuffer.allocate(1024);
            final long timeout = 60 * 60L;
            channel.read(buffer, timeout, TimeUnit.SECONDS, null, new CompletionHandler<Integer, Object>() {
                @Override
                public void completed(Integer result, Object attachment) {
                    if (result == -1) {
                        channelInactive(new ChannelHandler(channel, charset));
                        try {
                            channel.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    buffer.flip();
                    channelRead(new ChannelHandler(channel,charset), charset.decode(buffer));
                    buffer.clear();
                    channel.read(buffer,timeout,TimeUnit.SECONDS,null,this);
                }
                @Override
                public void failed(Throwable exc, Object attachment) {
                    exc.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Object attachment) {
        exc.getStackTrace();
    }

    public abstract void channelActive(ChannelHandler ctx) throws IOException;

    public abstract void channelInactive(ChannelHandler ctx);

    public abstract void channelRead(ChannelHandler ch,Object msg);
}
