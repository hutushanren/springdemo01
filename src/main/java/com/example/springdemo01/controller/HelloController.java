package com.example.springdemo01.controller;

import com.example.springdemo01.entity.User;
import com.example.springdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class HelloController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    public String hello() {
        System.out.println("==============");
        System.out.println("hello world");
        return "Hello World";
    }

    @GetMapping(value = "/getUser")
    public User getUser(@RequestParam("name") String name) {
        System.out.println("==============");
        User user = userService.selectByName(name);
        System.out.println(user);
        return user;
    }

}
