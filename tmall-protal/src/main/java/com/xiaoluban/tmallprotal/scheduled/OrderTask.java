package com.xiaoluban.tmallprotal.scheduled;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallprotal.vo.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author txb
 * @date 2021/3/3 11:28
 */
@Component
@Slf4j
public class OrderTask {

    @Value("${myRedis.toPay}")
    private String toPay;

    @Value("${myRedis.orderTimeout}")
    private long orderTimeout;

    @Value("${myRedis.toPayOrderLockPrefix}")
    private String toPayOrderLockPrefix;

    @Autowired
    private OmsOrderDao omsOrderDao;


//    @Value()

    @Autowired
    private RedisService redisService;

    public void timeoutHande(){

        /**
         *
         */
        double start=0;
        double now=(double)System.currentTimeMillis();
        Set<Object> timeoutSet=redisService.zRangByScore(toPay, start,now-orderTimeout);
        Iterator iterator=timeoutSet.iterator();
        while (iterator.hasNext()){
            String orderId= (String) iterator.next();
            String lockName=toPayOrderLockPrefix+orderId;
            if(redisService.gainLock(lockName,10L, TimeUnit.SECONDS)){
                //关闭订单
                OmsOrder order=new OmsOrder();
                order.setId(Long.parseLong(orderId));
                order.setStatus(OrderStatus.CLOSE.getState());

                omsOrderDao.updateByPrimaryKeySelective(order);

                redisService.zRemove(toPay,orderId);

                //解锁
                redisService.del(lockName);
            }
        }
    }
}
