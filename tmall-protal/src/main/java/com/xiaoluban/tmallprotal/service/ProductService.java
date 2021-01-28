package com.xiaoluban.tmallprotal.service;

import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private PmsProductDao pmsProductDao;

    public PmsProduct findProduct(Long id){
        return pmsProductDao.selectByPrimaryKey(id);
    }
}
