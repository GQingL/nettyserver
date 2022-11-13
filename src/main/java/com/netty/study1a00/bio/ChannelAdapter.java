package com.netty.study1a00.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 00:35
 */
public abstract class ChannelAdapter extends Thread {

    private Socket socket;

    private ChannelHandler channelHandler;

    private Charset charset;

    public ChannelAdapter(Socket socket,Charset charset){
        this.socket = socket;
        this.charset = charset;
        while (!socket.isConnected()){
            break;
        }
        channelHandler = new ChannelHandler(this.socket,charset);
        channelActive(channelHandler);
    }

    public void run(){
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), charset));
            String str = null;
            while ((str = input.readLine()) != null ){
                channelRead(channelHandler,str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void channelActive(ChannelHandler ctx);

    public abstract void channelRead(ChannelHandler ctx, Object msg);
}
