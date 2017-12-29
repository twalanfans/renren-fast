package com.ellison;

/**
 *
 * 业务枚举
 *
 * Created by zhuxuan on 2017/12/14
 */
public enum  BusinessEnum {


    _0000(0,"SUCCESS"),
    _0001(1,"token校验失败"),
    _0002(2,"token已过时"),
    _0003(3,"用户信息错误"),
    _0004(4,"系统异常"),
    _0005(5,"缺少必传参数"),
    _0006(6,"参数异常");


    public int code;
    public String message;

    BusinessEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
