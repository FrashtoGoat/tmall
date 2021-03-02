package com.xiaoluban.tmallprotal.controller;

import com.xiaoluban.tmallcommon.api.CommonResult;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.service.ProductService;
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


    @Autowired
    private OrderService orderService;


    @RequestMapping("createOrder")
    public CommonResult createOrder(UmsMember user, OmsOrder omsOrder, PmsProduct product){
        OmsOrder order=orderService.createOrder(user,omsOrder,product);
        return CommonResult.success(order);
    }

}
