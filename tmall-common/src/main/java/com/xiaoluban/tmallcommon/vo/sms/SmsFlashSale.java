package com.xiaoluban.tmallcommon.vo.sms;

import java.io.Serializable;
import lombok.Data;

/**
 * sms_flash_sale
 * @author 
 */
@Data
public class SmsFlashSale implements Serializable {
    private Long id;

    private String starttime;

    private String endtime;

    private String nextstarttime;

    /**
     * 0未开始 1进行中 2已结束
     */
    private String status;

    private static final long serialVersionUID = 1L;
}