package org.example.fs.service;


import org.example.fs.domain.dto.LoginUser;
import org.example.fs.domain.entity.User;
import org.example.fs.mapper.UserMapper;
import org.example.fs.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUserName(username);

        if(user == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        return new LoginUser(user,null);
    }

}
