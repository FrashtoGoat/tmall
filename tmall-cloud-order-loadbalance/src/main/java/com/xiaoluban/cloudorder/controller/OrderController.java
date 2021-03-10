package com.xiaoluban.cloudorder.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author txb
 * @date 2021/3/10 17:05
 */
@RestController
public class OrderController {

    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "order/createOrder/{id}")
    public String testCreatOrder(@PathVariable Long id){
        return "创建订单成功,端口号为："+port+",订单号为："+id;
    }
}
