package org.example.fs.controller.test;


import org.example.fs.domain.entity.User;
import org.example.fs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/fs")
    public String show(){
        User user = userMapper.selectUserByUserName("2");
        System.out.println(user);
        return "success";
    }

}
