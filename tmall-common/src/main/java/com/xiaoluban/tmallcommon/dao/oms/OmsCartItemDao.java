package com.xiaoluban.tmallcommon.dao.oms;

import com.xiaoluban.tmallcommon.vo.oms.OmsCartItem;

public interface OmsCartItemDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsCartItem record);

    int insertSelective(OmsCartItem record);

    OmsCartItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsCartItem record);

    int updateByPrimaryKey(OmsCartItem record);
}