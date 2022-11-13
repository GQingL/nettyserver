package com.netty.study1a00.bio.client;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * @author：liangguangqing
 * @date：2022/11/13 00:28
 */
public class BioClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.0.101", 7380);
            System.out.println("bio client start done");
            BioClientHandler bioClientHandler = new BioClientHandler(socket, Charset.forName("GBK"));
            bioClientHandler.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
