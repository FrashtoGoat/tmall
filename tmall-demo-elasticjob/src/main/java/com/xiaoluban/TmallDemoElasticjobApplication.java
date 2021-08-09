package com.xiaoluban;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author txb
 * @date 2021/1/25 16:49
 */
@SpringBootApplication
@MapperScan("com.xiaoluban.**.**.mapper")
public class TmallDemoElasticjobApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallDemoElasticjobApplication.class,args);
    }
}
