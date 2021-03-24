package com.xiaoluban.tmallprotal.config;

import com.xiaoluban.tmallcommon.config.BaseSwaggerConfig;
import com.xiaoluban.tmallcommon.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2API文档的配置
 * Created by txb 2021-03-24
 */
//@Configuration
//@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder()
                .apiBasePackage("com.xiaoluban.tmallprotal.controller")
                .title("tmall前台系统")
                .description("tmall前台相关接口文档")
                .contactName("txb")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
