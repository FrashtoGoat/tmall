package com.xiaoluban.tmallprotal.controller;

import com.xiaoluban.tmallcommon.api.CommonResult;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.service.UmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author txb
 * @date 2021/3/8 13:36
 * 用户管理
 */
@RestController
@RequestMapping("ums")
@Slf4j
public class UmsController {

    @Autowired
    private UmsService umsService;

    @RequestMapping("getOrderList/{userId}")
    public CommonResult<List<OmsOrder>> getOrderList(@PathVariable Integer userId){
        List<OmsOrder> list=umsService.getOrderList(userId);
        return CommonResult.success(list);
    }

    @RequestMapping("getUserInfo")
    public CommonResult<UmsMember> getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UmsMember umsMember=umsService.findUserByUserName(authentication.getName());
            return CommonResult.success(umsMember);
        }else{
            return CommonResult.failed("未能找到该用户信息");
        }
    }
}
