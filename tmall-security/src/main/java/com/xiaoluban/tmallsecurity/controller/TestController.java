package com.xiaoluban.tmallsecurity.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author txb
 * @date 2021/1/25 13:28
 */
//@Controller
@Api(value = "测试demo",tags = {"security","swagger"})
public class TestController {

    @ApiOperation(value = "进入登录页面")
    @RequestMapping("/login")
    public String login(){
        return "demo/loginpage";
    }

    @RequestMapping("/index")
    public String dologin(){
        return "demo/index";
    }

    @ApiOperation(value = "swagger测试")
    @RequestMapping("swaggerTest")
    @ResponseBody
    public String swaggerTest(){
        return "hello,swagger";
    }
}
