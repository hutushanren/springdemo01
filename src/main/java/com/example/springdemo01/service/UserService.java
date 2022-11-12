package com.example.springdemo01.service;

import com.example.springdemo01.entity.User;


public interface UserService {
    User selectByName(String name);
}
