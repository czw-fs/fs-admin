package org.example.fs.config.security.filter;

import org.example.fs.domain.dto.LoginUser;
import org.example.fs.domain.entity.User;
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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // todo 获取登录token
        String token = null;
        if(token == null){
            filterChain.doFilter(request, response);
            return;
        }



        LoginUser loginUser = new LoginUser(new User(1L, "aa", "333"), null);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,null);
        //如果这里设置了security上下文，那么不会走自定义认证逻辑
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //已登录用户，放行
        filterChain.doFilter(request, response);

    }
}
