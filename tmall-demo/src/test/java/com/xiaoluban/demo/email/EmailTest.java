package com.xiaoluban.demo.email;

import com.xiaoluban.BaseTestConfig;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author: TXB
 * @Date: 2021/8/2 9:25
 * @Description: *
 */
public class EmailTest extends BaseTestConfig {

    @Resource
    private EmailUtil emailUtil;

    @Resource
    private MailService mailService;

//    @Test
//    public void sendEmail(){
//        ToEmail toEmail=new ToEmail();
//        toEmail.setContent("正文内容");
//        toEmail.setSubject("主题");
//        toEmail.setTos(new String[]{"1271573554@qq.com"});
//        emailUtil.commonEmail(toEmail);
//    }

    @Test
    public void sendEmail2(){
        String to="1271573554@qq.com";
        String subject="测试修改发送人名称";
        String content="正文";
        mailService.sendSimpleTextMail(to,subject,content);
    }
}
