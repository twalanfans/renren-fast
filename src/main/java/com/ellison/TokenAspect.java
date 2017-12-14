package com.ellison;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 校验token切面
 *
 * Created by zhuxuan on 2017/12/14
 */
@Component
@Aspect
public class TokenAspect {


    @Pointcut("@annotation(com.ellison.TokenAnno)")
    public void anno(){}



    @Around("anno()")
    public Object round(ProceedingJoinPoint point) throws Throwable{

        Map<String, String> map = beforePoint(point);

        String userId = map.get("userId");

        String token = map.get("token");

        //校验token
        JwtUtils.validToken(userId,token);

        return point.proceed();
    }


    /**
     * 获取请求参数
     * @param point
     * @return
     */
    private Map<String,String> beforePoint(ProceedingJoinPoint point){

        Object[] args = point.getArgs();

        if(args == null || args.length == 0){
            throw new BusinessException(BusinessEnum._0005.code,BusinessEnum._0005.message,new Throwable());
        }

        Object args0 = args[0];

        if(args0.getClass() != String.class){
            throw new BusinessException(BusinessEnum._0006.code,BusinessEnum._0006.message,new Throwable());
        }

        String record = StringEscapeUtils.unescapeHtml((String) args0);

        JSONObject jo = JSON.parseObject(record);
        String userId = jo.getString("userId");
        String token = jo.getString("token");

        Map<String,String> respMap = new HashMap<>();
        respMap.put("userId",userId);
        respMap.put("token",token);
        return respMap;
    }

}
