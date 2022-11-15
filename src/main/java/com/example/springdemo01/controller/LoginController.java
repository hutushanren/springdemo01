package com.example.springdemo01.controller;

import com.example.springdemo01.common.CommonResult;
import com.example.springdemo01.dto.UserLoginParam;
import com.example.springdemo01.dto.UserRegisterParam;
import com.example.springdemo01.entity.User;
import com.example.springdemo01.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 注册接口
     * @param userRegisterParam
     * @return
     */
    @PostMapping("/register")
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

    /**
     * 用户登录
     * @param userLoginParam
     * @return
     */
    @PostMapping("/login")
    public CommonResult login(@RequestBody UserLoginParam userLoginParam) {
        if (userLoginParam == null) {
            return CommonResult.failed("注册参数不能为空");
        }
        String token = userService.login(userLoginParam);
        if (token == null) {
            return CommonResult.failed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        log.info("用户登录成功，username:{}", userLoginParam.getUsername());
        return CommonResult.success(tokenMap);
    }


}
