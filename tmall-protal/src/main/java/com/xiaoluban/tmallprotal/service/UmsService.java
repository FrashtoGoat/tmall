package com.xiaoluban.tmallprotal.service;

import com.xiaoluban.tmallcommon.vo.ums.UmsMember;

import java.util.List;

public interface UmsService {

    List<UmsMember> getAllUser();

    UmsMember findUserByUserName(String name);

}
