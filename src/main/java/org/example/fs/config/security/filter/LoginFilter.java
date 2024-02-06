package org.example.fs.config.security.filter;

import org.example.fs.domain.dto.LoginUser;
import org.example.fs.domain.entity.User;
import org.example.fs.utils.RedisCache;
import org.example.fs.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 判断用户是否登录的过滤器
 */
@Component
public class LoginFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // todo 获取登录token
        String token = request.getHeader("token");

        //没有token直接放行，进入认证环节
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }

        //获取redis中已登录的用户信息
        String UserUuId = tokenService.parseTokenGetRedisKey(token);
        LoginUser loginUser = (LoginUser)redisCache.getCacheObject(UserUuId);

        if(loginUser == null){
            filterChain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        //如果这里设置了security上下文，那么不会走自定义认证逻辑
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //刷新用户令牌时间
        tokenService.refreshToken(loginUser);

        //已登录用户，放行
        filterChain.doFilter(request, response);
    }
}
