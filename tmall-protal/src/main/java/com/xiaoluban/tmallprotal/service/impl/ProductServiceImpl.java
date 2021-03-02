package com.xiaoluban.tmallprotal.service.impl;

import com.github.pagehelper.PageInfo;
import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.util.PageUtil;
import com.xiaoluban.tmallcommon.vo.QueryVO;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.service.ProductService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: txb
 * @Date: 20210201
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private PmsProductDao pmsProductDao;

    @Override
    public PmsProduct findProduct(Long id){
        return pmsProductDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateNum(PmsProduct product) {
        return pmsProductDao.updateNum(product);
    }

    @Override
    public PageInfo<PmsProduct> getProducts(QueryVO queryVO){
        PageUtil.startPage(queryVO.getPageNum(),queryVO.getPageSize());
        List<PmsProduct> list=pmsProductDao.getProducts(queryVO);
        PageInfo<PmsProduct> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }




}
