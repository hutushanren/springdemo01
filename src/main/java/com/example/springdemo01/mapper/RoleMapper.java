package com.example.springdemo01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springdemo01.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getRolesByUserName(String username);
}
