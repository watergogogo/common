package com.aqua.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author water king
 * @time 2023/6/14
 */
@Data
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 4L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    private String avator;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    private Integer isDelete;
}
