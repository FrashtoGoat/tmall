package com.xiaoluban.tmallprotal.scheduled;

import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.dao.sms.SmsFlashProductDao;
import com.xiaoluban.tmallcommon.dao.sms.SmsFlashSaleDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.util.MyDateUtils;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashProduct;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashSale;
import com.xiaoluban.tmallprotal.service.TransService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: txb
 * @Date: 20210212
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduledTask {


    @Autowired
    private FlashSaleTask flashSaleTask;

    @Autowired
    private OrderTask orderTask;


//    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void checkFlashSale() {

       flashSaleTask.checkFlashSale();

    }

    /**
     * 每天执行1次，生成活动
     * 将状态为0、1的活动加入缓存
     * 瞎几把写，我也是服了我自己
     */
//    @Scheduled(cron = "* * 0 * * ?")
    private void generateFlashSale() {
       flashSaleTask.generateFlashSale();
    }


    @Scheduled(cron = "0/5 * * * * ?")
    private void orderTimeoutHander(){
        orderTask.timeoutHande();
    }





}
