package org.example.fs.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.fs.domain.entity.User;

public interface UserMapper {


    User selectUserByUserName(@Param("username") String username);
}
