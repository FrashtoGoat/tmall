package com.xiaoluban.tmallprotal.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @Author: txb
 * @Date: 20210212
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduledTask {

    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void checkFlashSale() {

        /**
         * 检查秒杀活动表，
         */
    }

}
