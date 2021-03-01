package com.xiaoluban.tmallprotal.service.impl;

import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.service.UmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @Author: txb
 * @Date: 20210226
 */
public class UserAuthServiceImpl implements UserDetailsService {

    @Autowired
    private UmsService umsService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UmsMember user=umsService.findUserByUserName(s);
//        if(user == null){
//            //如果用户查不到，返回null，由provider来抛出异常
//            return null;
//        }
        UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).build();
        return userDetails;
    }
}
