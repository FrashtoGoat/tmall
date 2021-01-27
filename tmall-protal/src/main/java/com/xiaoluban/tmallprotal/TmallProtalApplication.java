package com.xiaoluban.tmallprotal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: txb
 * @Date: 20210127
 */
@SpringBootApplication
@MapperScan("com.xiaoluban.*.dao")
public class TmallProtalApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallProtalApplication.class,args);
    }
}
