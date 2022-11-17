package com.example.springdemo01.service;

import com.example.springdemo01.dto.UserLoginParam;
import com.example.springdemo01.dto.UserRegisterParam;
import com.example.springdemo01.entity.Role;
import com.example.springdemo01.entity.User;

import java.util.List;


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

    /**
     * 获取所有角色
     */
    List<Role> getAllRole();
}
