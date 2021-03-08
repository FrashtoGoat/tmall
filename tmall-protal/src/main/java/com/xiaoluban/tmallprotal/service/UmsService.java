package com.xiaoluban.tmallprotal.service;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;

import java.util.List;

public interface UmsService {

    List<UmsMember> getAllUser();

    UmsMember findUserByUserName(String name);

    //获取用户的订单列表
    List<OmsOrder> getOrderList(Integer userId);
}
