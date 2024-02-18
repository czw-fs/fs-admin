package org.example.fs.controller.system;


import org.example.fs.domain.dto.LoginUser;
import org.example.fs.utils.RedisCache;
import org.example.fs.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class LogoutController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/logout")
    public void logout(String token){
        String key = tokenService.parseTokenGetRedisKey(token);
        //移除缓存信息
        redisCache.deleteObject(key);
    }

}
