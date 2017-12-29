package com.ellison;

/**
 *
 * 业务异常
 *
 * Created by zhuxuan on 2017/12/14
 */
public class BusinessException extends RuntimeException {


    private int code;

    public BusinessException() {
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
