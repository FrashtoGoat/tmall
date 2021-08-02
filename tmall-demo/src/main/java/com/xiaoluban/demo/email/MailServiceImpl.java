package com.xiaoluban.demo.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Map;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Value("${spring.mail.username}")
    private String username;
   /* @Value("${spring.mail.password}")
    private String password;*/
    @Resource
    private JavaMailSender mailSender;
    /**
     * 发送简单文本邮件
     */
    public boolean sendSimpleTextMail(String to, String subject, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
//            message.setFrom(username);
            message.setFrom(new InternetAddress(MimeUtility.encodeText("喜鹊招标网")+"<"+username+">").toString());

            mailSender.send(message);
            log.info("【文本邮件】发送成功！to={}", to);
            return true;
        }catch (Exception e){
            log.error("【文本邮件】发送失败！",e);
            return false;
        }
    }


}
