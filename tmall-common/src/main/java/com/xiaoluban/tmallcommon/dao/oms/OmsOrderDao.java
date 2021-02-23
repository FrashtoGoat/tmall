package com.xiaoluban.tmallcommon.dao.oms;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;

public interface OmsOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(OmsOrder record);

    int insertSelective(OmsOrder record);

    OmsOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OmsOrder record);

    int updateByPrimaryKey(OmsOrder record);
}