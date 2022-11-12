package com.example.springdemo01.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.springdemo01.mapper")
public class MapperConfig {
}
