package com.xiaoluban.tmallcommon.vo;

import java.io.Serializable;
import lombok.Data;

/**
 * pms_album
 * @author 
 */
@Data
public class PmsCommentReplay implements Serializable {
    private Long id;

    private String name;

    private String coverPic;

    private Integer picCount;

    private Integer sort;

    private String description;

    private static final long serialVersionUID = 1L;
}