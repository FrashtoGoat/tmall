package com.xiaoluban.tmallprotal.config;

import com.xiaoluban.tmallprotal.service.impl.UserAuthServiceImpl;
import com.xiaoluban.tmallsecurity.config.SecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: txb
 * @Date: 20210226
 */
@Configuration
public class MySecurityConfig extends SecurityConfig {


    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new UserAuthServiceImpl();
    }


    @Bean
    @Override
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
