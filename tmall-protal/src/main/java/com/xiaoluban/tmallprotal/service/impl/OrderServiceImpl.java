package com.xiaoluban.tmallprotal.service.impl;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.service.OrderService;
import com.xiaoluban.tmallprotal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: txb
 * @Date: 20210201
 */
@Service
public class OrderServiceImpl  implements OrderService {

    @Autowired
    private ProductService productService;

    /**
     * TODO 分布式事务  产品list
     * 创建订单
     *     1库存减少
     *     2订单生成
     *     3监控订单是否支付 15分钟内有效
     *     4支付超时，库存补偿
     */
    @Override
    public OmsOrder createOrder(UmsMember user, OmsOrder omsOrder, PmsProduct product) {

        int flag=productService.updateNum(product);

        return null;
    }
}
