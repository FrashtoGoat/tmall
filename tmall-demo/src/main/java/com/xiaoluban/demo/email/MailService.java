package com.xiaoluban.demo.email;


import java.util.Map;

public interface MailService {
    /**
     * 发送简单文本邮件
     */
    boolean sendSimpleTextMail(String to, String subject, String content);

//    /**
//     * 发送 HTML 邮件
//     */
//    boolean sendHtmlMail(String to, String subject, String content);
//
//    /**
//     * 发送带附件的邮件
//     */
//    boolean sendAttachmentMail(String to, String subject, String content,String... filePaths);
//
//
//
//    /**
//     *  发送带图片的邮件
//     */
//    boolean sendImgMail(String to, String subject, String content, Map<String, String> imgMap);
//
//    /**
//     * 发送模版邮件
//     */
//    boolean sendTemplateMail(String to, String subject, Map<String, Object> paramMap, String template);

}
