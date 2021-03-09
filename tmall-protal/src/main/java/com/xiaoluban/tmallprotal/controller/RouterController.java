package com.xiaoluban.tmallprotal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Slf4j
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

    @RequestMapping("ums/orderList/{userId}")
    public ModelAndView toOrderListPage(@PathVariable Long userId){

        Map<String,Object> model = new HashMap<>();
        model.put("userId",userId);
        return new ModelAndView("myorders",model);

    }

    @RequestMapping("order/common")
    public String commonPage(){
        return "common";
    }

    @RequestMapping("order/mine")
    public String mine(){
        return "mine";
    }

    @RequestMapping("test/getUserInfo")
    @ResponseBody
    public String userInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            log.info("当前用户信息"+authentication.toString());
            return currentUserName;
        }else{
            return "未能获取该用户信息";
        }
    }
}
