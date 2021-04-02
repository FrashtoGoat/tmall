package com.xiaoluban.demo.mybatisplus.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author txb
 * @since 2021-04-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private Integer age;

    private Integer userId;


}
