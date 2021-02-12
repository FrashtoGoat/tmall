package com.xiaoluban.tmallprotal.service;


import com.github.pagehelper.PageInfo;
import com.xiaoluban.tmallcommon.vo.QueryVO;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;

public interface ProductService {


    //分页查询商品

    //根据类别查询
    PageInfo<PmsProduct> getProducts(QueryVO queryVO);

    //根据名称查询

    //

}
