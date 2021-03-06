package com.xiaoluban.tmallprotal.controller;

import com.xiaoluban.tmallcommon.api.CommonResult;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.vo.OrderExtendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: txb
 * @Date: 20210120
 * 订单管理
 */
@RestController
@RequestMapping("order")
public class OrderController {


    @Autowired
    private OrderService orderService;


//    @RequestMapping("createOrder")
//    public CommonResult createOrder(UmsMember user, OmsOrder omsOrder, PmsProduct product){
//        OmsOrder order=orderService.createOrder(user,omsOrder,product);
//        return CommonResult.success(order);
//    }

    @RequestMapping("createOrder")
    public CommonResult createOrder(@RequestBody OrderExtendVO orderExtendVO){
//        OmsOrder order=orderService.createOrder(orderExtendVO.getUser(),orderExtendVO.getOrder(),orderExtendVO.getOrderItems(),orderExtendVO.getProducts());
        OmsOrder order=orderService.createOrder(orderExtendVO.getUser(),orderExtendVO.getOrder(),orderExtendVO.getProducts());
        return CommonResult.success(order);
    }

    @RequestMapping("pay")
    public CommonResult pay(@RequestBody OmsOrder order){
        order=orderService.pay(order);
        return CommonResult.success(order);
    }

}
