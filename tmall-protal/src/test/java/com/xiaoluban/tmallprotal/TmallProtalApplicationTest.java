package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.vo.QueueEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @Author: txb
 * @Date: 20210128
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TmallProtalApplicationTest {

    @Autowired
    private PmsProductDao pmsProductDao;

    @Test
    public void testMyBatis(){
        PmsProduct pmsProduct=pmsProductDao.selectByPrimaryKey(1l);
        System.out.println(pmsProduct.getDescription());
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void mybatis(){
        System.out.println(sqlSessionFactory);
    }


    @Autowired
    private RedisService redisService;

    @Test
    public void service(){
        System.out.println(redisService.gainLock("lockName",100L, TimeUnit.SECONDS));
    }

    @Test
    public void createOrder(){

    }

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 2000, initialDelay = 1000)
    public void send() {

        String message = "Hello World!";
        try {
            rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_PAY.getName(), message);
        }catch(Exception e) {
            System.out.println("连接失败");
            rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_PAY.getName(), message);
        }
        System.out.println(" [x] Sent '" + message + "'");
    }

    @Test
    public void test(){
        send();
    }
}
