package com.xiaoluban.tmallcommon.dao.pms;

import com.xiaoluban.tmallcommon.vo.QueryVO;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;

import java.util.List;

public interface PmsProductDao {
    int deleteByPrimaryKey(Long id);

    int insert(PmsProduct record);

    int insertSelective(PmsProduct record);

    PmsProduct selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PmsProduct record);

    int updateByPrimaryKey(PmsProduct record);

    List<PmsProduct> getProducts(QueryVO queryVO);
}