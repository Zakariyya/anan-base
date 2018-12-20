package com.anan.springboot.authshiro.authshiro.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({ "com.urundp.desktop.vda.auth.dao" })
public class AuthMybatisConfig {

}
