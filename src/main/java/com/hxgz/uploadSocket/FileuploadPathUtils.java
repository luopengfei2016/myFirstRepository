package com.hxgz.uploadSocket;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jackson
 * @description 上传文件路径工具类
 * @date 2019/7/1
 */
public class FileuploadPathUtils {
    private static final String TMP_PATH = "D:\\downloadFile\\";

    public static String getFilePath(){
        Date dt = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd\\aa-hh");
        String tempStr = simpleDateFormat.format(dt);
        return TMP_PATH + tempStr;
    }

}
