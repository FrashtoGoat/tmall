package com.xiaoluban.tmallprotal.service.impl;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.dao.ums.UmsMemberDao;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
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
    @Autowired
    private OmsOrderDao omsOrderDao;

    @Override
    public List<UmsMember> getAllUser() {
        return null;
    }

    @Override
    public UmsMember findUserByUserName(String name) {
        return umsMemberDao.findUserByName(name);
    }

    @Override
    public List<OmsOrder> getOrderList(Integer userId) {
        return omsOrderDao.getOrderList(userId);
    }
}
