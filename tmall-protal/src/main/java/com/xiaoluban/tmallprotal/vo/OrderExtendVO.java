package com.xiaoluban.tmallprotal.vo;

import com.xiaoluban.tmallcommon.vo.oms.OmsCartItem;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrderItem;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import lombok.Data;

import java.util.List;

/**
 * @Author: txb
 * @Date: 20210304
 */
@Data
public class OrderExtendVO {

    private OmsOrder order;
    private OmsOrderItem orderItem;
    private OmsCartItem cartItem;
    private PmsProduct product;
    private UmsMember user;

    private List<OmsCartItem> cartItems;
    private List<OmsOrderItem> orderItems;
    private List<PmsProduct> products;
}
