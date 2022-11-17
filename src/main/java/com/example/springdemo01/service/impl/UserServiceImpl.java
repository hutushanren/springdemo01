package com.example.springdemo01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.springdemo01.dto.UserLoginParam;
import com.example.springdemo01.dto.UserRegisterParam;
import com.example.springdemo01.entity.Role;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.mapper.RoleMapper;
import com.example.springdemo01.mapper.UserMapper;
import com.example.springdemo01.service.UserService;
import com.example.springdemo01.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    @Autowired
    private RoleMapper roleMapper;

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
    public String login (UserLoginParam userLoginParam) {
        String username = userLoginParam.getUsername();
        String password = userLoginParam.getPassword();
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            log.error("密码错误，username:{}", username);
            return null;
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    /**
     * 获取所有角色
     */
    @Override
    public List<Role> getAllRole() {
        List<Role> roleList = roleMapper.selectList(null);
        List<Role> result = roleList.stream().sorted().collect(Collectors.toList());
        return result;
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
