package com.xiaoluban.tmallprotal.controller;

import com.github.pagehelper.PageInfo;
import com.xiaoluban.tmallcommon.api.CommonResult;
import com.xiaoluban.tmallcommon.vo.QueryVO;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallprotal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: txb
 * @Date: 20210128
 * 商品管理
 */
@RestController
@RequestMapping("product/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("getProduct")
    public CommonResult getProduct(@RequestBody QueryVO queryVO){
        PageInfo<PmsProduct> pageInfo=productService.getProducts(queryVO);
        return CommonResult.success(pageInfo);
    }

    @RequestMapping("findProduct/{productId}")
    public CommonResult getProduct(@PathVariable Long productId){
        PmsProduct product=productService.findProduct(productId);
        return CommonResult.success(product);
    }


}
