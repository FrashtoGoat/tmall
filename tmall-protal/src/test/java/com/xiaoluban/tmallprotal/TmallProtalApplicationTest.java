package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.scheduled.OrderTask;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private OrderTask orderTask;

    @Test
    public void toPayOrder(){
        orderTask.timeoutHande();
    }


    @Value("${myRedis.toPay}")
    private String toPayKey;
    @Test
    public void impl(){
        double score=(double)new Date().getTime();
        redisService.zSortSet(toPayKey,"123456",score);
    }

    @Autowired
    private OmsOrderDao omsOrderDao;

    @Test
    public void getOrderList(){
        List<OmsOrder> list=omsOrderDao.getOrderList(1);
        System.out.println(list.size());

    }
}
