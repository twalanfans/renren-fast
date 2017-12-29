package com.ellison;

import io.jsonwebtoken.*;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 *
 * Jwt工具类
 *
 * Created by zhuxuan on 2017/12/14
 */
public class JwtUtils {

    //token过期时间(小时)
    private static final int EXPIRE_TIME = 24;

    //token秘钥
    private static final String SECRET_KEY = "xixiaishanghaha";

    //key of userId
    private static final String USER_KEY = "userId_key";

    /**
     * 生成token,返回给客户端.
     * @param userId
     */
    public static String buildToken(String userId){

        //当前时间
        Date nowDate = new Date();
        //过期时间
        Date expireDate = DateUtils.addHours(nowDate, EXPIRE_TIME);

        return Jwts.builder()
                .claim(USER_KEY, userId)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 校验客户端提交的token.
     * @param userId
     * @param token
     */
    public static void validToken(String userId, String token){

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token)
                    .getBody();

            Object object = claims.get(USER_KEY);

            if(object == null){
                throw new BusinessException(BusinessEnum._0003.code,BusinessEnum._0003.message, new Throwable());
            }

            String token_userId = object.toString();

            if(!userId.equals(token_userId)){
                throw new BusinessException(BusinessEnum._0003.code,BusinessEnum._0003.message, new Throwable());
            }

        } catch (ExpiredJwtException e) {
           //token过期
            throw new BusinessException(BusinessEnum._0002.code,BusinessEnum._0002.message, new Throwable());
        } catch (SignatureException e) {
            //token非法
            throw new BusinessException(BusinessEnum._0001.code,BusinessEnum._0001.message, new Throwable());
        }catch (Exception e){
            //其他异常
            throw new BusinessException(BusinessEnum._0004.code,BusinessEnum._0004.message, new Throwable());
        }


    }

}
