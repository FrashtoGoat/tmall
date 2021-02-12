package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.service.RedisService;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: txb
 * @Date: 20210128
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TmallProtalApplicationTest {

    @Autowired
    private PmsProductDao pmsProductDao;

    @Test
    public void testMyBatis(){
        PmsProduct pmsProduct=pmsProductDao.selectByPrimaryKey(1l);
        System.out.println(pmsProduct.getDescription());
    }

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void mybatis(){
        System.out.println(sqlSessionFactory);
    }


    @Autowired
    private RedisService redisService;

    @Test
    public void service(){
        System.out.println(redisService.hasKey("222"));
    }
}
