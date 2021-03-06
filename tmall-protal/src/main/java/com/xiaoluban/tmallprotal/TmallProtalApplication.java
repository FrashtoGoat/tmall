package com.xiaoluban.tmallprotal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author: txb
 * @Date: 20210127
 */
@SpringBootApplication
@ServletComponentScan(basePackages = "com.xiaoluban.tmallprotal")
@MapperScan("com.xiaoluban.*.dao")
public class TmallProtalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallProtalApplication.class,args);
    }
}
