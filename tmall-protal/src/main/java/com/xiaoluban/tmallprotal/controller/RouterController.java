package com.xiaoluban.tmallprotal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: txb
 * @Date: 20210127
 */
@Controller
public class RouterController {

    @RequestMapping("index")
    public String toIndex(){
        return "index";
    }


    @RequestMapping
    public String toProductDetailPage(){
        return "";
    }
}
