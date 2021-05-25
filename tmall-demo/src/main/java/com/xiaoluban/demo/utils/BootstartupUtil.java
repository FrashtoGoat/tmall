package com.xiaoluban.demo.utils;

import com.xiaoluban.demo.pojo.PersonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: TXB
 * @Date: 2021/5/25 15:50
 * @Description: *
 */
@Component
@Slf4j
public class BootstartupUtil implements InitializingBean {

    @Resource
    private PersonConfig personConfig;

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("personConfig------------:"+personConfig.getName());
        log.info("personConfig:"+personConfig.getAge());
        log.info("personConfig:"+personConfig.getCompany());
    }
}
