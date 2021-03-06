package com.xiaoluban.tmallprotal.service;


import com.github.pagehelper.PageInfo;
import com.xiaoluban.tmallcommon.vo.QueryVO;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;

import java.util.List;

public interface ProductService {


    //分页查询商品

    //根据类别查询
    PageInfo<PmsProduct> getProducts(QueryVO queryVO);

    //根据名称查询

    //
    PmsProduct findProduct(Long productId);

    //更新商品库存
    int updateNum(PmsProduct product);

    int batchUpdateNum(List<PmsProduct> productList);

}
