package com.ellison;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * 业务异常处理类
 *
 * Created by zhuxuan on 2017/12/14
 */

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public R handleBusinessException(BusinessException e){

        R r = new R();
        r.code=e.getCode();
        r.message = e.getMessage();
        return r;
    }



}
