package com.xiaoluban.demo.email;

import lombok.Data;

/**
 * @Author: TXB
 * @Date: 2021/8/2 9:21
 * @Description: *
 */
@Data
public class ToEmail {

    /**
     * 邮件接收方，可多人
     */
    private String[] tos;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;

}
