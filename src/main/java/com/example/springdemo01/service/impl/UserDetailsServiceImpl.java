package com.example.springdemo01.service.impl;

import com.example.springdemo01.dto.AdminUserDetails;
import com.example.springdemo01.entity.Role;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.mapper.RoleMapper;
import com.example.springdemo01.mapper.UserMapper;
import com.example.springdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Role> roleList = roleMapper.getRolesByUserName(username);
        User user = userMapper.getUserByName(username);
        if (Objects.isNull(user) || CollectionUtils.isEmpty(roleList)) {
            throw new UsernameNotFoundException("用户未找到，username：" + username);
        }
        return new AdminUserDetails(user, roleList);
    }
}
