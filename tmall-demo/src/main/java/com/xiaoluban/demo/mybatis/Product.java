package com.xiaoluban.demo.mybatis;

import java.io.Serializable;
import lombok.Data;

/**
 * product
 * @author 
 */
@Data
public class Product implements Serializable {
    /**
     * id
     */
    private Integer id;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 库存
     */
    private Integer stock;

    private String name;

    private static final long serialVersionUID = 1L;
}