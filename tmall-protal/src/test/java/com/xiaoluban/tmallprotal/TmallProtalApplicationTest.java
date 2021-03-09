package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.scheduled.OrderTask;
import com.xiaoluban.tmallprotal.vo.OrderStatus;
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

    @Test
    public void updateOrder(){
        OmsOrder order=new OmsOrder();


        Long orderId=1369213125686460416L;

        orderId=1368902465633648640L;
        order.setId(orderId);
        order.setStatus(OrderStatus.CLOSE.getState());

        int result;

//        result=omsOrderDao.updateByPrimaryKeySelective(order);
//        System.out.println(result);

        order.setBeforeStatus(OrderStatus.TOPAY.getState());
        result=omsOrderDao.updateOrderStatus(order);
        System.out.println(result);
    }

    @Value("${myRedis.toPay}")
    private String toPay;
    @Test
    public void redisHdel(){

//        Long orderId=1369213125686460416L;

        Long orderId=1368902465633648640L;
        orderId=1369278568428732416L;
        //移除
        Long count=redisService.zRemove(toPay,orderId);

        System.out.println("删除数量："+count);
        //移除
        count=redisService.zRemove(toPay,orderId+"");
        System.out.println("删除数量："+count);

    }


    @Test
    public void OrderTask(){
        orderTask.timeoutHande();
    }
    @Value("${myRedis.orderTimeout}")
    private long orderTimeout;

    @Test
    public void timeCalculate(){
        double now=(double)System.currentTimeMillis();
        System.out.println(now-orderTimeout);

    }

}
