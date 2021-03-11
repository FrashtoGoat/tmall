package com.xiaoluban.tmallcloudgateway;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author txb
 * @date 2021/3/11 10:46
 */
public class NormalTest {

    @Test
    public void now(){
        System.out.println(LocalDateTime.now());
        System.out.println(new Date());

        System.out.println(ZonedDateTime.now());
    }

}
