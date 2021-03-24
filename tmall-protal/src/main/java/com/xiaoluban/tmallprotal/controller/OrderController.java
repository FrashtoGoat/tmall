package com.xiaoluban.tmallprotal.controller;

import com.xiaoluban.tmallcommon.api.CommonResult;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.vo.OrderExtendVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "/order", description = "Operations about order")
public class OrderController {


    @Autowired
    private OrderService orderService;


//    @RequestMapping("createOrder")
//    public CommonResult createOrder(UmsMember user, OmsOrder omsOrder, PmsProduct product){
//        OmsOrder order=orderService.createOrder(user,omsOrder,product);
//        return CommonResult.success(order);
//    }

    @PostMapping("createOrder")
    @ApiOperation(
            value = "createOrder",
            notes = "创建订单",
            tags = {"Pet Store"})
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
