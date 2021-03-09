package com.xiaoluban.tmallprotal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: txb
 * @Date: 20210127
 */
@Controller
@Api(value = "router",tags = {"security","swagger"})
public class RouterController {


    @ApiOperation(value = "进入登录页面")
    @RequestMapping("/login")
    public String login(){
        return "loginpage";
    }

    @RequestMapping("/index")
    public String dologin(){
        return "index";
    }

    @ApiOperation(value = "swagger测试")
    @RequestMapping("swaggerTest")
    @ResponseBody
    public String swaggerTest(){
        return "hello,swagger";
    }


    @RequestMapping("product/productDetails/{productId}")
    public ModelAndView toProductDetailPage(@PathVariable Long productId){

        Map<String,Object> model = new HashMap<>();
        model.put("productId",productId);
        return new ModelAndView("product",model);

    }

    @RequestMapping("order/common")
    public String commonPage(){
        return "common";
    }

    @RequestMapping("order/mine")
    public String mine(){
        return "mine";
    }
}
