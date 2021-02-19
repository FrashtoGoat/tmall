package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.util.MyDateUtils;
import org.junit.Test;

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
}
