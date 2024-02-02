package org.example.fs.controller.system;


import org.example.fs.domain.dto.LoginDto;
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
    public UniformResult<String> login(@RequestBody LoginDto user){

        String login = loginServcie.login(user);
        return UniformResult.success(login);
    }
}
