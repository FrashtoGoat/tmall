package com.xiaoluban.tmallsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author txb
 * @date 2021/1/25 16:49
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class TmallSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmallSecurityApplication.class,args);
    }
}
