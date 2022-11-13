package com.example.springdemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo01.dto.UserLoginParam;
import com.example.springdemo01.dto.UserRegisterParam;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.mapper.UserMapper;
import com.example.springdemo01.service.UserService;
import com.example.springdemo01.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User selectByName(String name) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }

    /**
     * 注册功能
     */
    @Override
    public User register(UserRegisterParam userRegisterParam) throws Exception {
        if (Objects.isNull(userRegisterParam)) {
            log.info("注册参数有误");
            throw new Exception("注册参数有误");
        }
        User user = this.getUserFromRegisterParam(userRegisterParam);
        User userByName = userMapper.getUserByName(user.getName());
        if (userByName != null) {
            return null;
        }
        userMapper.insert(user);
        return user;
    }

    /**
     * 登录功能
     */
    @Override
    public String login(UserLoginParam userLoginParam) {
        return null;
    }

    private User getUserFromRegisterParam(UserRegisterParam userRegisterParam) {
        if (Objects.isNull(userRegisterParam)) {
            return null;
        }
        User user = new User();
        user.setName(userRegisterParam.getName());
        user.setPassword(passwordEncoder.encode(userRegisterParam.getPassword()));
        user.setDeleted(false);
        return user;
    }
}
