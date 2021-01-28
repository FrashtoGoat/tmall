package com.xiaoluban.tmallprotal;


import com.xiaoluban.tmallcommon.dao.pms.PmsProductDao;
import com.xiaoluban.tmallcommon.vo.pms.PmsProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: txb
 * @Date: 20210128
 */
@RunWith(SpringRunner.class)
//@ContextConfiguration(locations={"classpath:applicationContext.xml"}) //加载配置文件
public class SpringTest {

    @Autowired
    private PmsProductDao pmsProductDao;

    @Test
    public void testMyBatis(){
        PmsProduct pmsProduct=pmsProductDao.selectByPrimaryKey(1l);
        System.out.println(pmsProduct.getDescription());
    }
}
