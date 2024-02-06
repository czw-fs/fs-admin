package org.example.fs.controller.system;

import org.example.fs.config.security.SecurityUtils;
import org.example.fs.domain.entity.User;
import org.example.fs.domain.vo.UniformResult;
import org.example.fs.service.user.UserService;
import org.example.fs.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("getInfo")
    public UniformResult getInfo()
    {
        User user = SecurityUtils.getLoginUser().getUser();
        return UniformResult.success(user);
    }

}
