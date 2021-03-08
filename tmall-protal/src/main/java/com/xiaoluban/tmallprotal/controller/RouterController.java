package com.xiaoluban.tmallprotal.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


//    @RequestMapping
//    public String toProductDetailPage(){
//        return "";
//    }

    @RequestMapping("order/common")
    public String commonPage(){
        return "common";
    }

    @RequestMapping("order/mine")
    public String mine(){
        return "mine";
    }
}
