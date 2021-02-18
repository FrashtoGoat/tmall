package com.xiaoluban.tmallprotal.scheduled;

import com.xiaoluban.tmallcommon.dao.sms.SmsFlashSaleDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.util.MyDateUtils;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashSale;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

         * 2检查活动是否开发、检查活动是否结束
         */

        Map<Object,Object> map=redisService.hGetAll(flashsaleMonitorKey);

        if(map==null){
            log.info("----redis异常，退出监控任务----");
            return;
        }
        if(map.size()==0){
            log.info("----没有需要监控的订单，退出监控任务----");
            return;
        }

        Iterator<Map.Entry<Object, Object>> it = map.entrySet().iterator();

        while (it.hasNext()){

            try {
                Map.Entry<Object, Object> entry = it.next();
                String actiId=(String)entry.getKey();
                SmsFlashSale saleVO= (SmsFlashSale) entry.getValue();

                String lockName=flashsaleMonitorKey+actiId;
                try {
                    if(redisService.gainLock(lockName,10L, TimeUnit.SECONDS)){
                        String status=saleVO.getStatus();
                        switch (status){
                            case "0":
                                Date startTime=MyDateUtils.strTranDate(saleVO.getStarttime());
                                if(MyDateUtils.compareTwoDate(new Date(),startTime)){
                                   //更新活动状态
                                    SmsFlashSale sale=new SmsFlashSale();
                                    sale.setId(saleVO.getId());
                                    sale.setStatus("1");
                                    smsFlashSaleDao.updateByPrimaryKey(sale);
                                    //更新监控状态
                                   saleVO=smsFlashSaleDao.selectByPrimaryKey(saleVO.getId());
                                   redisService.hSet(flashsaleMonitorKey,actiId,saleVO);
                                }
                                break;
                            case "1":
                                Date endTime=MyDateUtils.strTranDate(saleVO.getEndtime());
                                if(MyDateUtils.compareTwoDate(new Date(),endTime)){
                                    //更新活动状态
                                    SmsFlashSale sale=new SmsFlashSale();
                                    sale.setId(saleVO.getId());
                                    sale.setStatus("2");
                                    smsFlashSaleDao.updateByPrimaryKey(sale);
                                    //移除监控
                                    redisService.hDel(flashsaleMonitorKey,actiId);
                                }
                                break;
                            default:
                                log.info("未知状态，TODO");
                        }
                    }
                } catch (Exception e) {
                    log.error("redis异常",e);
                }


            } catch (Exception e) {
                log.error("定时任务异常", e);
            }

        }

    }

    /**
     * 每天执行1次，生成活动
     * 将状态为0、1的活动加入缓存
     */
    @Scheduled(cron = "* * 0 * * ?")
    private void generateFlashSale() {

        //        List<SmsFlashSale> acti=smsFlashSaleDao.getMonitorActis();
//        Map<String,Object> map=new HashMap<>();
//        for(SmsFlashSale flashsale:acti){
//            map.put(flashsale.getId()+"",flashsale);
//        }
//        redisService.hSetAll(flashsaleMonitorKey,map);
//
    }

}
