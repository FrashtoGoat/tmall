package com.xiaoluban.tmallprotal.service;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;

/**
 * @Author: txb
 * @Date: 20210127
 */
public interface OrderService {


    OmsOrder createOrder(UmsMember user, OmsOrder omsOrder, PmsProduct product);


}
