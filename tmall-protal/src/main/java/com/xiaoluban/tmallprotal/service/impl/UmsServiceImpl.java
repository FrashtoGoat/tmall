package com.xiaoluban.tmallprotal.service.impl;

import com.xiaoluban.tmallcommon.dao.ums.UmsMemberDao;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import com.xiaoluban.tmallprotal.service.UmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: txb
 * @Date: 20210226
 */
@Service
public class UmsServiceImpl implements UmsService {

    @Autowired
    private UmsMemberDao umsMemberDao;

    @Override
    public List<UmsMember> getAllUser() {
        return null;
    }

    @Override
    public UmsMember findUserByUserName(String name) {
        return umsMemberDao.findUserByName(name);
    }
}
