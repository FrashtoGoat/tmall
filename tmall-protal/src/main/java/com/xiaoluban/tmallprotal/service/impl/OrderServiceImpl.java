package com.xiaoluban.tmallprotal.service.impl;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.exception.TradeException;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.service.ProductService;
import com.xiaoluban.tmallprotal.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: txb
 * @Date: 20210201
 */
@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private ProductService productService;

    @Autowired
    private OmsOrderDao omsOrderDao;

    @Autowired
    private RedisService redisService;

    @Value("${myRedis.orderTimeout}")
    private Long orderTimeout;

//    @Value("${myRedis.toPayPrefix}")
//    private String toPayPrefix;

    @Value("${myRedis.toPay}")
    private String toPayKey;

    @Value("${myRedis.orderListPrefix}")
    private String orderListPrefix;


    /**
     * TODO 分布式事务
     * 创建订单
     *     1库存减少
     *     2订单生成
     *     3监控订单是否支付 15分钟内有效
     *     4支付超时，库存补偿
     */
    @Override
    public OmsOrder createOrder(UmsMember user, OmsOrder omsOrder, PmsProduct product) {

        int succ=productService.updateNum(product);
        if(succ==0){
            //库存更新失败
            throw new TradeException("库存更新失败");
        }
        Date now =new Date();
        omsOrder.setCreateTime(now);

        omsOrder.setMemberId(user.getId());
        omsOrder.setMemberUsername(user.getUsername());

        omsOrder.setStatus(OrderStatus.TOPAY.getState());

        int point=product.getGiftPoint();
        omsOrder.setIntegration(point);

        omsOrderDao.insertSelective(omsOrder);

        /**
         * 订单倒计时
         * 用redis的zsortSet结构
         */
        //String key=toPayPrefix+omsOrder.getId();
       //redisService.set(key,omsOrder,orderTimeout);
        double score=(double)now.getTime();
        redisService.zSortSet(toPayKey,omsOrder.getId(),score);


        //用户缓存订单列表+1订单
        String orderListKey=orderListPrefix+omsOrder.getId();
        redisService.hSet(orderListKey,omsOrder.getId()+"",omsOrder);

        return omsOrder;
    }

    @Override
    public OmsOrder pay(OmsOrder order) {

        /**
         * 1更新订单状态
         * 2发布消息到消息队列
         */
        omsOrderDao.updateByPrimaryKeySelective(order);

        return null;
    }

}
