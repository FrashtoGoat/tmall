package com.xiaoluban.tmallcommon.vo.sms;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * sms_flash_product
 * @author 
 */
@Data
public class SmsFlashProduct extends SmsFlashProductKey implements Serializable {
    /**
     * 限时购价格
     */
    private BigDecimal flashPromotionPrice;

    /**
     * 限时购数量
     */
    private Integer flashPromotionCount;

    /**
     * 每人限购数量
     */
    private Integer flashPromotionLimit;

    /**
     * 排序
     */
    private Integer sort;

    private static final long serialVersionUID = 1L;
}