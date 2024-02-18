package org.example.fs.controller.system;

import org.example.fs.domain.vo.UniformResult;
import org.example.fs.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/getIp")
    public UniformResult<String> getIp(HttpServletRequest request){
        String ipAddr = "127.0.0.1";
        return UniformResult.success(ipAddr);
    }

}
