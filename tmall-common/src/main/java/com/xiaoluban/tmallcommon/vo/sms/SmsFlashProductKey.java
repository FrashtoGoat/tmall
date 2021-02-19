package com.xiaoluban.tmallcommon.vo.sms;

import java.io.Serializable;
import lombok.Data;

/**
 * sms_flash_product
 * @author 
 */
@Data
public class SmsFlashProductKey implements Serializable {
    private Long flashPromotionId;

    private Long productId;

    private static final long serialVersionUID = 1L;
}