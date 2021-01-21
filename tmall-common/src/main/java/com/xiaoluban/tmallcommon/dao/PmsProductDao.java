package com.xiaoluban.tmallcommon.dao;

import com.xiaoluban.tmallcommon.vo.PmsProduct;

public interface PmsProductDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProduct record);

    int insertSelective(PmsProduct record);

    PmsProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProduct record);

    int updateByPrimaryKey(PmsProduct record);
}