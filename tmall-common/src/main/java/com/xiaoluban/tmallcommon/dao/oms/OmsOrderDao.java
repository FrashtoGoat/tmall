package com.xiaoluban.tmallcommon.dao.oms;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;

import java.util.List;

public interface OmsOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    OmsOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);

    List<OmsOrder> getOrderList(Integer userId);

    int updateOrderStatus(OmsOrder record);
}