package com.xiaoluban.tmallprotal.service.point;

import com.xiaoluban.tmallcommon.dao.ums.UmsMemberDao;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.ums.UmsMember;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: txb
 * @Date: 20210307
 * 积分模块 可拆分为单独项目
 */
@Component
@RabbitListener(queues = "tmall.order.pay")
@Slf4j
public class PointService {

    @Autowired
    private UmsMemberDao umsMemberDao;

    //订单支付成功后增加积分
    @RabbitHandler
    public void calculateForUser(OmsOrder order) {

        log.info("PointHandler消费者收到消息  : " + order.toString());

        UmsMember user=new UmsMember();
        user.setId(order.getMemberId());
        user.setIntegration(order.getIntegration());

        umsMemberDao.updateIntegration(user);

        log.info(user.getId()+"新增积分"+user.getIntegration()+"成功");

    }
}
