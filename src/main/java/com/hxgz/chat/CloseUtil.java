package com.hxgz.chat;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Jackson
 * @description 关闭流工具类
 * @date 2019/6/15
 */
public class CloseUtil {

    public static void closeAll(Closeable... io){
        for (Closeable temp:io) {
            if(null!=temp){
                try {
                    temp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
