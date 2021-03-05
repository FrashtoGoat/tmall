package com.xiaoluban.tmallprotal.service.impl;

import com.github.pagehelper.PageInfo;
import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.util.PageUtil;
import com.xiaoluban.tmallcommon.vo.QueryVO;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.exception.TradeException;
import com.xiaoluban.tmallprotal.service.ProductService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public int batchUpdateNum(List<PmsProduct> productList) {
        for(PmsProduct product:productList){
            int result=pmsProductDao.updateNum(product);
            if(result<=0){
                throw new TradeException("库存更新失败");
            }
        }
        return 1;
    }

    @Override
    public PageInfo<PmsProduct> getProducts(QueryVO queryVO){
        PageUtil.startPage(queryVO.getPageNum(),queryVO.getPageSize());
        List<PmsProduct> list=pmsProductDao.getProducts(queryVO);
        PageInfo<PmsProduct> pageInfo=new PageInfo<>(list);
        return pageInfo;
    }




}
