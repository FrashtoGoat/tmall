package com.xiaoluban.demo;

import com.xiaoluban.BaseTestConfig;
import com.xiaoluban.tmallcommon.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author txb
 * @date 2021/5/31 20:47
 * @describtion *
 */
@Slf4j
public class RedisTest extends BaseTestConfig {

    @Autowired
    private RedisService redisService;

    String lockPrefix="locklock_";
    String userId="100540";

    @Test
    public void service(){
        System.out.println(redisService.gainLock("lockName",100L, TimeUnit.SECONDS));
    }

    @Test
    public void gainLockTest(){
        lockAndRelease();
    }

    private void lockAndRelease(){

        String lock=lockPrefix+userId;
        int count=4;
        for(int i=0;i<count;i++){
//            new Thread(()->{
//                try {
//                    boolean gainLock=redisService.gainLock(lock,60L, TimeUnit.SECONDS);
//                    if(gainLock){
//                        //获取到锁
//                        System.out.println("获取到锁");
//                    }else{
//                        System.out.println("没有获取到锁");
//                    }
//                } catch (Exception e) {
//                    log.error("获取锁异常",e);
//                } finally {
//                    redisService.releaseLock(lock);
//                    System.out.println("锁释放成功");
//                }
//            }).start();

            boolean gainLock= redisService.gainLock(lock,60L, TimeUnit.SECONDS);
            if(gainLock){
                //获取到锁
                System.out.println("获取到锁");
//                redisService.releaseLock(lock);
//                System.out.println("锁释放成功");
            }else{
                System.out.println("没有获取到锁");
            }
        }
    }

}
