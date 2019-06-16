package com.hxgz.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author Jackson
 * @description 发送消息线程
 * @date 2019/6/15
 */
public class Send implements Runnable{

    //控制台输入流
    private BufferedReader console;
    //管道输出流
    private DataOutputStream dos;
    //线程标识
    private boolean isRunning = true;

    public Send() {
        console =new BufferedReader(new InputStreamReader(System.in));
    }
    public Send(Socket client) {
        this();
        try {
            dos = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            isRunning = false;
            CloseUitl.closeAll();
        }
    }
    //从控制台获取需要发送的数据（没有前台页面时用）
    private String getMsgFromConsole(){
        try {
            return console.readLine();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return "";
    }

    //发送数据
    public void send(){
        String msg = getMsgFromConsole();
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
     * @author Jackson
     * @description //TODO 线程体
     * @date 2019/6/15
     * @param []
     * @return void
     */
    @Override
    public void run() {
        while (isRunning){
            send();
        }
    }
}
