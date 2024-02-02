package org.example.fs.config.security.handle;

import org.example.fs.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 自定义认证逻辑
 */
@Component
public class LoginHandle implements AuthenticationProvider {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();//获取前端传递的用户名
        String password = (String)authentication.getCredentials();//获取前端传递的密码

        //获取库中查出来的用户信息
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        //判断前端传递的密码和库中查的密码是否一致
        //todo 密码加密判断加密
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        boolean matches = bCryptPasswordEncoder.matches(password, userDetails.getPassword());
//        if(!matches){
//            throw new BadCredentialsException("密码不正确，请重新输入");
//        }

        boolean equals = password.equals(userDetails.getPassword());
        if(!equals){
            throw new BadCredentialsException("密码不正确，请重新输入");
        }

        //todo 权限
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null , userDetails.getAuthorities());

        // 认证成功后设置springSecurity上下文
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        //注意这个值为true才会将你的LoginHandle认证逻辑放到authenticationManager.authenticate(token);的认证调用链中
        return true;
    }
}
