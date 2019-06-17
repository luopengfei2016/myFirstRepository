package com.hxgz.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Jackson
 * @description 网络编程在线聊天客户端
 * @date 2019/6/15
 */
public class Client {

    /**
     * @param args
     * @throws IOException
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("请输入名称:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if(name.equals("")){
            return;
        }

        Socket client = new Socket("localhost",9999);
        new Thread(new Send(client,name)).start(); //一条路径
        new Thread(new Receive(client)).start(); //一条路径

    }
}
