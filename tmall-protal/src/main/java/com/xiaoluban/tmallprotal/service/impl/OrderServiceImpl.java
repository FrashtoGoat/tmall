package com.xiaoluban.tmallprotal.service.impl;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.util.IDUtils;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrderItem;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.service.TransService;
import com.xiaoluban.tmallprotal.vo.OrderStatus;
import com.xiaoluban.tmallprotal.vo.QueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: txb
 * @Date: 20210201
 */
@Service
@Slf4j
public class OrderServiceImpl  implements OrderService {

//    @Autowired
//    private ProductService productService;

    @Autowired
    private OmsOrderDao omsOrderDao;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TransService transService;

    //使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${myRedis.orderTimeout}")
    private Long orderTimeout;

//    @Value("${myRedis.toPayPrefix}")
//    private String toPayPrefix;

    @Value("${myRedis.toPay}")
    private String toPayKey;

    @Value("${myRedis.orderListPrefix}")
    private String orderListPrefix;

    @Value("${myRedis.toPayOrderLockPrefix}")
    private String toPayOrderLockPrefix;

    @Value("${myRedis.toPay}")
    private String toPay;


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

        BigDecimal totalAmount = new BigDecimal(0);
        int totalPoint = 0;


        for(PmsProduct product:productList){

            item=new OmsOrderItem();
            p=new PmsProduct();


            item.setOrderId(orderId);

            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setProductQuantity(product.getQuantity());
            item.setGiftGrowth(product.getGiftPoint());
            item.setProductPrice(product.getPrice());
            item.setProductCategoryId(product.getProductCategoryId());
            item.setProductPic(product.getPic());


            item.setIntegrationAmount(new BigDecimal(product.getGiftPoint()));
            BigDecimal itemAmout=product.getPrice().multiply(new BigDecimal(product.getQuantity()));
            item.setRealAmount(itemAmout);
            totalAmount=totalAmount.add(itemAmout);
            totalPoint+=product.getGiftPoint();


            p.setId(product.getId());
            p.setQuantity(product.getQuantity());

            orderItems.add(item);
            updateProList.add(p);
        }


        omsOrder.setMemberId(user.getId());
        omsOrder.setMemberUsername(user.getUsername());

        //omsOrder.setStatus(OrderStatus.TOPAY.getState());
        omsOrder.setPayAmount(totalAmount);
        omsOrder.setTotalAmount(totalAmount);
        omsOrder.setIntegration(totalPoint);

        transService.createOrder(omsOrder,orderItems,updateProList);

        //根据订单状态进行下一步
//        int status=omsOrder.getStatus();
        switch (omsOrder.getStatus()){
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
            case 3:
                //中间状态都省略了
                //发布消息到队列
                rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_PAY.getExchange(), "", omsOrder);
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

        Long orderId=order.getId();
        log.info("进入未支付订单->支付逻辑："+orderId);

        String lockName=toPayOrderLockPrefix+orderId;
        int times=3;
        while (times>0){
            if(redisService.gainLock(lockName,10L, TimeUnit.SECONDS)){

                log.info("订单获取到锁："+orderId);
                /**
                 * 1更新订单状态
                 * 2发布消息到消息队列
                 */
                order.setStatus(OrderStatus.FINISH.getState());
                order.setModifyTime(new Date());

                int flag=omsOrderDao.updateByPrimaryKeySelective(order);

                if(flag==1){
                    //移除
                    redisService.zRemove(toPay,orderId);

                    //消息通知
                    rabbitTemplate.convertAndSend(QueueEnum.QUEUE_ORDER_PAY.getExchange(), "", order);

                    //解锁
                    redisService.del(lockName);
                }

                times=0;

            }else{
                times--;
            }
        }

        return order;
    }



}
