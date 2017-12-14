package com.ellison;

import java.util.HashMap;

/**
 * 全局结果集
 *
 * Created by zhuxuan on 2017/12/14
 */
public class R extends HashMap<String,Object>{

    int code;

    String message;

    Object data;

    static R ok(){
        R r = new R();
        r.code = BusinessEnum._0000.code;
        r.message = BusinessEnum._0000.message;
        return r;
    }

    static R ok(Object data){
        R r = new R();
        r.code = BusinessEnum._0000.code;
        r.message = BusinessEnum._0001.message;
        r.data = data;
        return r;
    }


    static R error(int code, String message){
        R r = new R();
        r.code = code;
        r.message = message;
        return r;
    }

}
