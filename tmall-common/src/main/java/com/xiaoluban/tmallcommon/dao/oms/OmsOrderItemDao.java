package com.xiaoluban.tmallcommon.dao.oms;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrderItem;

import java.util.List;

public interface OmsOrderItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrderItem record);

    int insertSelective(OmsOrderItem record);

    OmsOrderItem selectByPrimaryKey(Long id);

    List<OmsOrderItem> getList(Long orderId);

    int updateByPrimaryKeySelective(OmsOrderItem record);

    int updateByPrimaryKey(OmsOrderItem record);
}