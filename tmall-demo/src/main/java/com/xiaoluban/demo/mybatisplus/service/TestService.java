package com.xiaoluban.demo.mybatisplus.service;

import com.xiaoluban.demo.mybatisplus.entity.Person;
import com.xiaoluban.demo.mybatisplus.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: txb
 * @Date: 20210408
 */
@Service
public class TestService {

    @Resource
    private TranscationTestService transcationTestService;

    public void testTwoSameUpgradeMethod(Person person, User user){
        transcationTestService.updatePersonAndUser(person,user);
    }
}
