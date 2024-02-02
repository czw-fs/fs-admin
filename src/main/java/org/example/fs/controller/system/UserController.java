package org.example.fs.controller.system;

import org.example.fs.service.user.UserService;
import org.example.fs.service.user.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {


    @Autowired
    private UserService userService;


}
