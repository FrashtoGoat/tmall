package com.xiaoluban.tmallprotal;

import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: txb
 * @Date: 20210128
 */
@SpringBootTest
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
}