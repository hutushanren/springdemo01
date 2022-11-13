package com.example.springdemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo01.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    User getUserByName(String username);
}
