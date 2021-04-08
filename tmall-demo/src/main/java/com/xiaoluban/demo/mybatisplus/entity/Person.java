package com.xiaoluban.demo.mybatisplus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Person {

    @TableId(value = "user_id")
    private Integer userId;

    private Integer age;

    private String name;



}
