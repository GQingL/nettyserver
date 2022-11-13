package com.netty.study1a00.bio.serve;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 00:59
 */
public class BioServer extends Thread{

    private ServerSocket serverSocket = null;

    public static void main(String[] args) {
        BioServer bioServer = new BioServer();
        bioServer.start();
    }

    public void run(){
        try {
            serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(7380));
            System.out.println("bio server start done");
            while (true){
                Socket socket = serverSocket.accept();
                BioServerHandler bioServerHandler = new BioServerHandler(socket, Charset.forName("GBK"));
                bioServerHandler.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
