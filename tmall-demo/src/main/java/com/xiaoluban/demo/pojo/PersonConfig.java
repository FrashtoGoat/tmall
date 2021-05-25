package com.xiaoluban.demo.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: TXB
 * @Date: 2021/5/25 15:44
 * @Description: *
 */
@Component
@ConfigurationProperties(
        prefix = "person"
)
@Data
public class PersonConfig {

    private String name;

    private Integer age;

    private String company;
}
