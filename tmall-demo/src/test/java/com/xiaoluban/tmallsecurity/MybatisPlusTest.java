package com.xiaoluban.tmallsecurity;

import com.xiaoluban.demo.TmallDemoApplication;
import com.xiaoluban.demo.mybatisplus.mapper.UserMapper;
import com.xiaoluban.demo.mybatisplus.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author: TXB
 * @Date: 2021/4/2 14:48
 * @Description: *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=TmallDemoApplication.class)
public class MybatisPlusTest {


    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
