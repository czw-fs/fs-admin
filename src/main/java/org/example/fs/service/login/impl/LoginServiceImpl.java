package org.example.fs.service.login.impl;

import org.example.fs.domain.dto.LoginDto;
import org.example.fs.domain.dto.LoginUser;
import org.example.fs.service.login.LoginService;
import org.example.fs.utils.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public String login(LoginDto loginDto) {

        //检查用户名和密码
        preCheckLogin(loginDto);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate)){
            throw new RuntimeException("用户名或密码错误");
        }

        //获取token
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LoginUser loginUser = (LoginUser) principal;
        String token = tokenService.createToken(loginUser);

        return token;
    }

    public void preCheckLogin(LoginDto loginDto){
        String username = loginDto.getUsername().trim();
        String password = loginDto.getPassword().trim();

        if(!StringUtils.hasLength(username) && !StringUtils.hasLength(password)){
            throw new RuntimeException("用户名或密码不能为空");
        }
    }
}
