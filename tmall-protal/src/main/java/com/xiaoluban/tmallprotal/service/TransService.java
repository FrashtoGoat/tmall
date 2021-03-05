package com.xiaoluban.tmallprotal.service;

import com.xiaoluban.tmallcommon.dao.oms.OmsOrderDao;
import com.xiaoluban.tmallcommon.dao.oms.OmsOrderItemDao;
import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.dao.sms.SmsFlashProductDao;
import com.xiaoluban.tmallcommon.dao.sms.SmsFlashSaleDao;
import com.xiaoluban.tmallcommon.vo.oms.OmsCartItem;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrder;
import com.xiaoluban.tmallcommon.vo.oms.OmsOrderItem;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashProduct;
import com.xiaoluban.tmallcommon.vo.sms.SmsFlashSale;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: txb
 * @Date: 20210219
 * 事务服务
 */
@Component
public class TransService {

    @Autowired
    private SmsFlashSaleDao smsFlashSaleDao;
//    @Autowired
//    private SmsFlashProductDao smsFlashProductDao;
    @Autowired
    private OmsOrderDao omsOrderDao;
    @Autowired
    private OmsOrderItemDao omsOrderItemDao;
    @Autowired
    private PmsProductDao pmsProductDao;
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Transactional(rollbackFor = {Exception.class})
    public void createFlashSale(SmsFlashSale flashSale, List<SmsFlashProduct> saleProducts){
        SqlSession session=sqlSessionFactory.openSession(ExecutorType.BATCH);
        SmsFlashProductDao flashProductDao=session.getMapper(SmsFlashProductDao.class);
        for (int i = 0; i < saleProducts.size(); i++)
        {
            flashProductDao.insertSelective(saleProducts.get(i));
            if ((i+1)%1000 == 0)
            {
                session.flushStatements();
            }
        }
        session.flushStatements();

        smsFlashSaleDao.insertSelective(flashSale);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void createOrder(OmsOrder order, List<OmsOrderItem> orderItems, List<PmsProduct> products){

        SqlSession session=sqlSessionFactory.openSession(ExecutorType.BATCH);
        OmsOrderItemDao orderItemDao=session.getMapper(OmsOrderItemDao.class);
        PmsProductDao productDao=session.getMapper(PmsProductDao.class);

        omsOrderDao.insertSelective(order);

        for(int i=0;i<orderItems.size();i++){
            orderItemDao.insertSelective(orderItems.get(i));
        }

        for(int i=0;i<products.size();i++){
            productDao.updateNum(products.get(i));
        }

        session.flushStatements();

    }
}
