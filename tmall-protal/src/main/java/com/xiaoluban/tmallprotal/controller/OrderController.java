package com.xiaoluban.tmallprotal.controller;

import com.xiaoluban.tmallcommon.api.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: txb
 * @Date: 20210120
 */
@RestController
@RequestMapping("order")
public class OrderController {


    /**
     * TODO 分布式事务
     * 创建订单
     *     1库存减少
     *     2订单生成
     *     3监控订单是否支付 15分钟内有效
     *     4支付超时，库存补偿
     */
    @RequestMapping("createOrder")
    public CommonResult createOrder(){
        return null;
    }

}
