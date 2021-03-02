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
    private SmsFlashSaleDao smsFlashSaleDao;
    @Autowired
    private PmsProductDao pmsProductDao;
    @Autowired
    private SmsFlashProductDao smsFlashProductDao;
    @Autowired
    private RedisService redisService;
    @Autowired
    private TransService transService;

    @Value("${myRedis.flashsaleMonitorKey}")
    private String flashsaleMonitorKey;
    @Value("${myRedis.flashsaleIncrKey}")
    private String flashsaleIncrKey;


//    @Scheduled(cron = "0/5 * * * * ?")
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
     * 瞎几把写，我也是服了我自己
     */
    @Scheduled(cron = "* * 0 * * ?")
    private void generateFlashSale() {

        try {
            int num=10;
            Date todayStart=MyDateUtils.getTodayZero();
            Date startTime,endTime;
            SmsFlashSale flashSale;
            List<SmsFlashProduct> saleProducts;
            for(int i=0;i<num;i++){

                Long saleId=redisService.incr(flashsaleIncrKey,1L);

                startTime=MyDateUtils.getDateAfterHours(todayStart,i*2);
                endTime=MyDateUtils.getDateAfterHours(todayStart,(i+1)*2);
                flashSale=new SmsFlashSale();
                flashSale.setStatus("0");
                flashSale.setStarttime(MyDateUtils.formatDateTime_(startTime));
                flashSale.setEndtime(MyDateUtils.formatDateTime_(endTime));
                flashSale.setId(saleId);

                saleProducts=new ArrayList<>();
                Integer productId=new Random().nextInt(17);
                //PmsProduct product=pmsProductDao.selectByPrimaryKey(productId.longValue());
                SmsFlashProduct smsFlashProduct=new SmsFlashProduct();
                smsFlashProduct.setSort(1);
                smsFlashProduct.setFlashPromotionCount(50);
                smsFlashProduct.setFlashPromotionPrice(BigDecimal.valueOf(50));
                smsFlashProduct.setFlashPromotionLimit(5);
                smsFlashProduct.setProductId(productId.longValue());
                smsFlashProduct.setFlashPromotionId(saleId);

                saleProducts.add(smsFlashProduct);

                transService.createFlashSale(flashSale,saleProducts);

            }


            List<SmsFlashSale> acti=smsFlashSaleDao.getMonitorActis();
            Map<String,Object> map=new HashMap<>();
            for(SmsFlashSale flashsale:acti){
                map.put(flashsale.getId()+"",flashsale);
            }
            redisService.hSetAll(flashsaleMonitorKey,map);


        } catch (Exception e) {
            log.error("模拟创建秒杀活动异常",e);
        }

    }

}
