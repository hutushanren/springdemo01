package com.example.springdemo01.service;

import com.example.springdemo01.dto.UserLoginParam;
import com.example.springdemo01.dto.UserRegisterParam;
import com.example.springdemo01.entity.User;


public interface UserService {
    User selectByName(String name);

    /**
     * 注册功能
     */
    User register(UserRegisterParam userRegisterParam) throws Exception;

    /**
     * 登录功能
     */
    String login(UserLoginParam userLoginParam);
}
