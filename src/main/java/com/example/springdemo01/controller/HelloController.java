package com.example.springdemo01.controller;

import com.alibaba.fastjson.JSON;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        log.info("hello world");
        return "Hello World";
    }

    @GetMapping(value = "/getUser")
    public User getUser(@RequestParam("name") String name) {
        User user = userService.selectByName(name);
        log.info("user: {}", JSON.toJSONString(user));
        return user;
    }

}
