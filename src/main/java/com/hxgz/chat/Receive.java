package com.hxgz.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Jackson
 * @description 接收消息线程
 * @date 2019/6/15
 */
public class Receive implements Runnable {

    //输入流
    private DataInputStream dis;
    //线程标识
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket client) {
        try {
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUitl.closeAll();
        }
    }

    /**
     * @param []
     * @return java.lang.String
     * @author Jackson
     * @description //TODO 接收数据
     * @date 2019/6/15
     */
    public String receive() {
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
     * @param []
     * @return void
     * @author Jackson
     * @description //TODO 线程体
     * @date 2019/6/15
     */
    @Override
    public void run() {
        while (isRunning) {
            receive();
        }
    }
}
