package com.xiaoluban.demo.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: TXB
 * @Date: 2021/8/2 9:21
 * @Description: *
 */
@Component
public class EmailUtil {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender mailSender;




    public void commonEmail(ToEmail toEmail) {
        //创建简单邮件消息
        SimpleMailMessage message = new SimpleMailMessage();
        //谁发的
        message.setFrom(from);
        //谁要接收
        message.setTo(toEmail.getTos());
        //邮件标题
        message.setSubject(toEmail.getSubject());
        //邮件内容
        message.setText(toEmail.getContent());
        try {
            mailSender.send(message);
            System.out.println("邮件发送成功");
        } catch (MailException e) {
            e.printStackTrace();
        }

    }


}
