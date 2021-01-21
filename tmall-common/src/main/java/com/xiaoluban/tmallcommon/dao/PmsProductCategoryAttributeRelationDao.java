package com.xiaoluban.tmallcommon.dao;

import com.xiaoluban.tmallcommon.vo.PmsProductCategoryAttributeRelation;

public interface PmsProductCategoryAttributeRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProductCategoryAttributeRelation record);

    int insertSelective(PmsProductCategoryAttributeRelation record);

    PmsProductCategoryAttributeRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProductCategoryAttributeRelation record);

    int updateByPrimaryKey(PmsProductCategoryAttributeRelation record);
}