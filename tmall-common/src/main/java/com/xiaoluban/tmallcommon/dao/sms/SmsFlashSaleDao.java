package com.xiaoluban.tmallcommon.dao.sms;

import com.xiaoluban.tmallcommon.vo.sms.SmsFlashSale;

import java.util.List;

public interface SmsFlashSaleDao {

    List<SmsFlashSale> getMonitorActis();

    int deleteByPrimaryKey(Long id);

    int insert(SmsFlashSale record);

    int insertSelective(SmsFlashSale record);

    SmsFlashSale selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SmsFlashSale record);

    int updateByPrimaryKey(SmsFlashSale record);
}