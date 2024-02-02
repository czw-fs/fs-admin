package org.example.fs.controller.login;


import org.example.fs.domain.dto.LoginDto;
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
    public String login(@RequestBody LoginDto user){
        return loginServcie.login(user);
    }
}
