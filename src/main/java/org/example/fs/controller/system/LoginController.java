package org.example.fs.controller.system;


import org.example.fs.config.security.SecurityUtils;
import org.example.fs.domain.dto.LoginDto;
import org.example.fs.domain.vo.LoginUserVo;
import org.example.fs.domain.vo.UniformResult;
import org.example.fs.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginServcie;

    @PostMapping("/login")
    public UniformResult<LoginUserVo> login(@RequestBody LoginDto user) {
        String token = loginServcie.login(user);

        LoginUserVo loginUserVo = new LoginUserVo();
        loginUserVo.setUserId(SecurityUtils.getUserId());
        loginUserVo.setUsername(SecurityUtils.getLoginUser().getUsername());
        loginUserVo.setToken(token);

        return UniformResult.success(loginUserVo);
    }
}
