package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.util.MyDateUtils;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Duration;

/**
 * @Author: txb
 * @Date: 20210217
 */
public class NormalTest {

    @Test
    public void duration(){
        long time=11L;
        Duration.ofDays(time);
    }

    @Test
    public void getNow(){
        System.out.println(MyDateUtils.getDateNow());
    }


    @Test
    public void bccryptTest(){
        String pass = "123456";
        BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
        String hashPass = bcryptPasswordEncoder.encode(pass);
        System.out.println(hashPass);

        boolean f = bcryptPasswordEncoder.matches("123456",hashPass);
        System.out.println(f);
    }
}
