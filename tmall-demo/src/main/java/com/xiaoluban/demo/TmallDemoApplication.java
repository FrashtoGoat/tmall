package com.xiaoluban.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author txb
 * @date 2021/1/25 16:49
 */
@SpringBootApplication
@MapperScan({"com.baomidou.mybatisplus.samples.quickstart.mapper","com.xiaoluban.**.**.mapper"})
public class TmallDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallDemoApplication.class,args);
    }
}
