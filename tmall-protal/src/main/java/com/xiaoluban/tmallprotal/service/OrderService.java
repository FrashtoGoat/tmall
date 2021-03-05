package com.xiaoluban.tmallprotal.service;

import com.xiaoluban.tmallcommon.vo.oms.OmsCartItem;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrderItem;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;

import java.util.List;

/**
 * @Author: txb
 * @Date: 20210127
 */
public interface OrderService {


    OmsOrder createOrder(UmsMember user, OmsOrder omsOrder, List<PmsProduct> productList);

   // OmsOrder createOrder(UmsMember user, OmsOrder omsOrder, List<OmsOrderItem> orderItems,List<PmsProduct> productList);
   // OmsOrder createOrder(UmsMember user, OmsOrder omsOrder, List<OmsOrderItem> orderItems);

    OmsOrder pay(OmsOrder order);


}
