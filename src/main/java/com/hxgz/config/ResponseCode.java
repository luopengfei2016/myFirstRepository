package com.hxgz.config;

/**
 * Created by hr
 */
public enum  ResponseCode {

//    public static final int OK=20000;//成功
//    public static final int ERROR =20001;//失败
//    public static final int LOGINERROR =20002;//用户名或密码错误
//    public static final int ACCESSERROR =20003;//权限不足
//    public static final int REMOTEERROR =20004;//远程调用失败
//    public static final int REPERROR =20005;//重复操作

    SUCCESS(0, "SUCCESS"),
    ERROR(1, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT"),
    FORBIDDEN(3, "FORBIDDEN");

    private final int code;
    private final String desc;


    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
