package com.xiaoluban.tmallprotal.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.util.IDUtils;
import com.xiaoluban.tmallcommon.vo.oms.OmsCartItem;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrderItem;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.exception.TradeException;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.service.ProductService;
import com.xiaoluban.tmallprotal.service.TransService;
import com.xiaoluban.tmallprotal.vo.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Autowired
    private TransService transService;

    @Value("${myRedis.orderTimeout}")
    private Long orderTimeout;

//    @Value("${myRedis.toPayPrefix}")
//    private String toPayPrefix;

    @Value("${myRedis.toPay}")
    private String toPayKey;

    @Value("${myRedis.orderListPrefix}")
    private String orderListPrefix;


    /**
     * TODO 分布式事务 改进
     * 创建订单
     *     1库存减少
     *     2订单生成
     *     3监控订单是否支付 15分钟内有效
     *     4支付超时，库存补偿
     */
    @Override
    public OmsOrder createOrder(UmsMember user, OmsOrder omsOrder,List<PmsProduct> productList) {

        List<OmsOrderItem> orderItems=new ArrayList<>();
        List<PmsProduct> updateProList=new ArrayList<>();

        Date now =new Date();
        Long orderId= IDUtils.getNextIdBySnow();
        omsOrder.setId(orderId);
        omsOrder.setCreateTime(now);

        OmsOrderItem item;
        PmsProduct p;

        BigDecimal totalAmount = null;
        int totalPoint = 0;


        for(PmsProduct product:productList){

            item=new OmsOrderItem();
            p=new PmsProduct();

            item.setOrderId(orderId);
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setProductQuantity(product.getQuantity());
            item.setIntegrationAmount(new BigDecimal(product.getGiftPoint()));

            totalAmount=totalAmount.add(product.getPrice().multiply(new BigDecimal(product.getQuantity())));
            totalPoint+=product.getGiftPoint();


            p.setId(product.getId());
            p.setQuantity(product.getQuantity());

            orderItems.add(item);
            updateProList.add(p);
        }


        omsOrder.setMemberId(user.getId());
        omsOrder.setMemberUsername(user.getUsername());

        omsOrder.setStatus(OrderStatus.TOPAY.getState());
        omsOrder.setTotalAmount(totalAmount);
        omsOrder.setIntegration(totalPoint);

        transService.createOrder(omsOrder,orderItems,updateProList);

        //根据订单状态进行下一步
//        int status=omsOrder.getStatus();
        switch (OrderStatus.TOSEND.getState()){
            case 0:
                /**
                 * 订单倒计时
                 * 用redis的zsortSet结构
                 */
                //String key=toPayPrefix+omsOrder.getId();
                //redisService.set(key,omsOrder,orderTimeout);
                double score=(double)now.getTime();
                redisService.zSortSet(toPayKey,omsOrder.getId(),score);
                break;
            case 1:
                //TODO 发布消息到队列
                break;
            default:

        }

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
