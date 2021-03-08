package com.xiaoluban.tmallcommon.dao.ums;

import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;

import java.util.List;

public interface UmsMemberDao {

    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(UmsMember record);

    UmsMember selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UmsMember record);

    int updateByPrimaryKey(UmsMember record);

    UmsMember findUserByName(String userName);

    int updateIntegration(UmsMember user);


}