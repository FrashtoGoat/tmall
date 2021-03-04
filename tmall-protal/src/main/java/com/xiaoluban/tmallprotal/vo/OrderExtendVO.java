package com.xiaoluban.tmallprotal.vo;

import com.xiaoluban.tmallcommon.vo.oms.OmsCartItem;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
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
    private List<OmsCartItem> orderItems;
    private PmsProduct product;
    private UmsMember user;

}
