package com.xiaoluban.tmallprotal.scheduled;

import com.xiaoluban.tmallcommon.dao.sms.SmsFlashSaleDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: txb
 * @Date: 20210212
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduledTask {

    @Autowired
    private SmsFlashSaleDao smsFlashSaleDao;
    @Autowired
    private RedisService redisService;

    @Value("${myRedis.flashsaleMonitorKey}")
    private String flashsaleMonitorKey;


    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void checkFlashSale() {

        /**
         * 检查秒杀活动表
         * 1将状态为0、1的活动加入缓存  TODO
         * 2检查活动是否开发、检查活动是否结束
         */
//        List<SmsFlashSale> acti=smsFlashSaleDao.getMonitorActis();
//        Map<String,Object> map=new HashMap<>();
//        for(SmsFlashSale flashsale:acti){
//            map.put(flashsale.getId()+"",flashsale);
//        }
//        redisService.hSetAll(flashsaleMonitorKey,map);
//
        //

    }

}
