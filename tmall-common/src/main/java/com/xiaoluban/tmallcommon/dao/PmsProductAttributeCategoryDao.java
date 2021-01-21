package com.xiaoluban.tmallcommon.dao;

import com.xiaoluban.tmallcommon.vo.PmsProductAttributeCategory;

public interface PmsProductAttributeCategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductAttributeCategory record);

    int insertSelective(PmsProductAttributeCategory record);

    PmsProductAttributeCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductAttributeCategory record);

    int updateByPrimaryKey(PmsProductAttributeCategory record);
}