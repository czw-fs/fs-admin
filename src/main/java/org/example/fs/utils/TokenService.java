package org.example.fs.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.example.fs.domain.Contants.Common;
import org.example.fs.domain.dto.LoginUser;
import org.example.fs.domain.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * token验证处理
 *
 * @author ruoyi
 */
@Component
public class TokenService {

    // 令牌秘钥
    @Value("${token.secret}")
    private String secret;

    // 令牌有效期（默认30分钟）
    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private RedisCache redisCache;


    //创建token，并将用户信息存入redis
    public String createToken(LoginUser loginUser){

        String uuid = UUID.randomUUID().toString();
        loginUser.setUuid(uuid);

        HashMap<String, Object> map = new HashMap<>();
        map.put("uuid", uuid);
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secret)
                .setClaims(map)
                .compact();
        //将用户信息存入redis
        redisCache.setCacheObject(uuid,loginUser,expireTime,TimeUnit.MINUTES);
        return jwt;
    }

    //解析前端发来的token，获取userId
    public String parseTokenGetRedisKey(String token){
        Jws<Claims> jws = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token);
        String uuid = (String) jws.getBody().get("uuid");
        return uuid;
    }

    //刷新令牌有效期
    public void refreshToken(LoginUser loginUser) {
        redisCache.setCacheObject(loginUser.getUuid(), loginUser, expireTime, TimeUnit.MINUTES);
    }
}
