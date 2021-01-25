package com.xiaoluban.tmallsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author txb
 * @date 2021/1/25 13:28
 */
@Controller
public class TestController {

    @RequestMapping("/login")
    public String login(){
        return "demo/login";
    }

    @RequestMapping("index")
    public String dologin(){
        return "demo/index";
    }
}
