package cn.edu.ecnu.stu.bookstore.utils;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {

    /** 盐值*/
    private static final String SING="!@#%^$*$%#^$#%^%$$#QWBADasda881";

    //生成令牌
    public static String getToken(Object object){
        //获取日历对象
        Calendar calendar= Calendar.getInstance();
        //默认1天过期
        calendar.add(Calendar.DATE,1);
        //新建一个JWT的Builder对象
        JWTCreator.Builder builder = JWT.create();
        String jsonStr = JSON.toJSONString(object);
        builder.withClaim("data",jsonStr);
        //设置过期时间和签名
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(SING));
    }
    /**
     * 验签并返回DecodedJWT
     * @param token  令牌
     */
    public static Object getTokenInfo(String token,Class<?> clazz){
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
        return JSON.parseObject(jwt.getClaim("data").asString(), clazz);
    }

}

