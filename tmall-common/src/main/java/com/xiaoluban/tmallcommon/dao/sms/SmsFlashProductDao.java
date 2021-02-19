package com.xiaoluban.tmallcommon.dao.sms;

import com.xiaoluban.tmallcommon.vo.sms.SmsFlashProduct;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashProductKey;

public interface SmsFlashProductDao {
    int deleteByPrimaryKey(SmsFlashProductKey key);

    int insert(SmsFlashProduct record);

    int insertSelective(SmsFlashProduct record);

    SmsFlashProduct selectByPrimaryKey(SmsFlashProductKey key);

    int updateByPrimaryKeySelective(SmsFlashProduct record);

    int updateByPrimaryKey(SmsFlashProduct record);
}