package com.xiaoluban.tmallprotal.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 将后端雪花生成的高精度Long转成String
 * 全局方法，不需要单独在每个字段上面写@JsonSerialize(using = ToStringSerializer.class)
 */
//@Configuration
public class JacksonConfig {


  @Bean
  @Primary
  @ConditionalOnMissingBean(ObjectMapper.class)
  public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder)
  {
    ObjectMapper objectMapper = builder.createXmlMapper(false).build();

    // 全局配置序列化返回 JSON 处理
    SimpleModule simpleModule = new SimpleModule();
    //JSON Long ==> String
    simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
    objectMapper.registerModule(simpleModule);
    return objectMapper;
  }

}