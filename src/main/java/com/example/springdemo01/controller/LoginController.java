package com.example.springdemo01.controller;

import com.example.springdemo01.common.CommonResult;
import com.example.springdemo01.dto.UserRegisterParam;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @PostMapping("/register")
    @ResponseBody
    public CommonResult<User> register(@RequestBody UserRegisterParam userRegisterParam) {
        if (userRegisterParam == null) {
            return CommonResult.failed("注册参数不能为空");
        }
        User user;
        try {
            user = userService.register(userRegisterParam);
        } catch (Exception e) {
            return CommonResult.failed(e.getMessage());
        }
        return CommonResult.success("注册成功", user);
    }


}
