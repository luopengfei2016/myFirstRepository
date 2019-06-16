package com.hxgz.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Jackson
 * @description 网络编程在线聊天客户端
 * @date 2019/6/15
 */
public class Client {

    public static void main(String[] args) throws IOException {

        System.out.println("请输入名称:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        if(name.equals("")){
            return;
        }

        //创建客户端  指定服务器和端口
        Socket client = new Socket("localhost",8888);

        new Thread(new Send(client)).start();//客户端发送数据
        new Thread((new Receive(client))).start();//客户端接收数据
    }

}
