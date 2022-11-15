package com.example.springdemo01.service.impl;

import com.example.springdemo01.dto.AdminUserDetails;
import com.example.springdemo01.entity.Constant;
import com.example.springdemo01.entity.Role;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.entity.UserRoleRelation;
import com.example.springdemo01.mapper.RoleMapper;
import com.example.springdemo01.mapper.UserMapper;
import com.example.springdemo01.mapper.UserRoleRelationMapper;
import com.example.springdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Role> roleList = roleMapper.getRolesByUserName(username);
        User user = userMapper.getUserByName(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户未找到，username：" + username);
        }
        // 当user是新注册还没有role时，赋予普通用户权限
        if (CollectionUtils.isEmpty(roleList)) {
            roleList = new ArrayList<>();
            Role role = roleMapper.selectById(Constant.NORMAL_ROLE_ID);
            roleList.add(role);
            UserRoleRelation userRoleRelation = new UserRoleRelation(user.getId(), role.getId());
            userRoleRelationMapper.insert(userRoleRelation);
        }
        return new AdminUserDetails(user, roleList);
    }
}
