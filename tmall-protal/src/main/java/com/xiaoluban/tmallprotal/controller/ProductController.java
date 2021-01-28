package com.xiaoluban.tmallprotal.controller;

import com.xiaoluban.tmallcommon.api.CommonResult;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: txb
 * @Date: 20210128
 */
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("testMybatis/{id}")
    public CommonResult findProduct(@PathVariable Long id){
        PmsProduct product=productService.findProduct(id);
        return CommonResult.success(product);
    }


}
