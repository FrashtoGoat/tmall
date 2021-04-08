package com.xiaoluban.tmallsecurity;

import com.xiaoluban.demo.TmallDemoApplication;
import com.xiaoluban.demo.mybatisplus.entity.Person;
import com.xiaoluban.demo.mybatisplus.mapper.UserMapper;
import com.xiaoluban.demo.mybatisplus.entity.User;
import com.xiaoluban.demo.mybatisplus.service.TestService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: TXB
 * @Date: 2021/4/2 14:48
 * @Description: *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=TmallDemoApplication.class)
public class MybatisPlusTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    //mybatisplus回显自增id
    @Test
    public void getUserId(){
        User user=new User();
        user.setAge(25);
        user.setEmail("127157344");
        user.setName("TTT");
        System.out.println("userId"+user.getId());
        userMapper.insert(user);
        System.out.println("userId"+user.getId());
    }

    @Resource
    private TestService testService;

    @Test
    public void testTwoSameUpgradeMethod(){
        Person person=new Person();
        person.setUserId(1);
        person.setName("T");
        User user=new User();
        user.setId(1L);
        user.setName("T");
        testService.testTwoSameUpgradeMethod(person,user);
    }

}
