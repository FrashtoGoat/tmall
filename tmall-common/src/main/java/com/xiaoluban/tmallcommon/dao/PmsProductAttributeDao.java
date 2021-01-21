package com.xiaoluban.tmallcommon.dao;

import com.xiaoluban.tmallcommon.vo.PmsProductAttribute;

public interface PmsProductAttributeDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttribute record);

    int insertSelective(PmsProductAttribute record);

    PmsProductAttribute selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttribute record);

    int updateByPrimaryKey(PmsProductAttribute record);
}