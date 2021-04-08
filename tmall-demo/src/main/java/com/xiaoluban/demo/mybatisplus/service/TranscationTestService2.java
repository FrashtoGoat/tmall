package com.xiaoluban.demo.mybatisplus.service;

import com.xiaoluban.demo.mybatisplus.entity.Person;
import com.xiaoluban.demo.mybatisplus.entity.User;
import com.xiaoluban.demo.mybatisplus.mapper.PersonMapper;
import com.xiaoluban.demo.mybatisplus.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: txb
 * @Date: 20210408
 */
@Service
public class TranscationTestService2 {

    @Resource
    private PersonMapper personMapper;
    @Resource
    private UserMapper userMapper;



//    @Transactional(rollbackFor = {Exception.class})
    public void updatePerson(Person person){
        personMapper.updateById(person);
    }

//    @Transactional(rollbackFor = {Exception.class})
    public void updateUser(User user){
        userMapper.updateById(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void updatePersonAndUser(Person person,User user){
        updateUser(user);
//        int i=1/0;
        updatePerson(person);
        int i=1/0;
    }

}
