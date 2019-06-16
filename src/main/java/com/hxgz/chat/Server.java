package com.hxgz.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jackson
 * @description 网络编程在线沟通服务端
 * @date 2019/6/15
 */
public class Server {

    private static final Logger log = LoggerFactory.getLogger(Server.class);

    private List<MyChannel> all = new ArrayList<MyChannel>();

    public static void main(String[] args) throws IOException {
        new Server().start();
    }
    public void start() throws IOException {
        //创建服务器  指定端口号
        ServerSocket socket = new ServerSocket(8888);
        while (true){
            //接收客户端连接  阻塞式的
            Socket client = socket.accept();
            log.info("一个客户端建立连接");
            MyChannel channel = new MyChannel(client);
            all.add(channel);
            new Thread(channel).start();
        }
    }
    /**
     * 一个客户端 一条道路
     * 1、输入流
     * 2、输出流
     * @author Administrator
     *
     */
    private class MyChannel implements Runnable{

        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;

        private String name;

        public MyChannel(Socket client) {
            try {
                dis = new DataInputStream(client.getInputStream());
                dos = new DataOutputStream(client.getOutputStream());

                this.name =dis.readUTF();
                this.send("欢迎您进入聊天室");
                sendOthers(this.name+"进入了聊天室",true);
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
                CloseUitl.closeAll();
            }
        }
        /**
         * @author Jackson
         * @description //TODO 读取数据
         * @date 2019/6/16
         * @param []
         * @return void
         */
        public String receive(){
            String msg = "";
            try {
                msg = dis.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                isRunning = false;
                CloseUitl.closeAll();
            }
            return msg;
        }
        /**
         * @author Jackson
         * @description //TODO 发送数据
         * @date 2019/6/16
         * @param []
         * @return void
         */
        public void send(String msg){
            if(null!=msg&&!"".equals(msg)){
                try {
                    dos.writeUTF(msg);
                    dos.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                    isRunning = false;
                    CloseUitl.closeAll();
                }
            }
        }

        /**
         * 发送给其他客户端
         */
        private void sendOthers(String msg,boolean sys){
            //是否为私聊 约定
            if(msg.startsWith("@")&& msg.indexOf(":")>-1 ){ //私聊
                //获取name
                String name =msg.substring(1,msg.indexOf(":"));
                String content = msg.substring(msg.indexOf(":")+1);
                for(MyChannel other:all){
                    if(other.name.equals(name)){
                        other.send(this.name+"对您悄悄地说:"+content);
                    }
                }
            }else{
                //遍历容器
                for(MyChannel other:all){
                    if(other ==this){
                        continue;
                    }
                    if(sys){ //系统信息
                        other.send("系统信息:"+msg);
                    }else{
                        //发送其他客户端
                        other.send(this.name+"对所有人说:"+msg);
                    }
                }
            }
        }

        @Override
        public void run() {
            while (isRunning){
                sendOthers(receive(),false);
            }
        }
    }
}
