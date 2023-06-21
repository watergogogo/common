package com.aqua.common.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author water king
 * @time 2023/6/15
 */
@Data
@TableName("orders")
public class Order implements Serializable {

    private static final Long serialVersionUID = 23456L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String name;

    private BigDecimal price;

    private String img;

    private int count;

    private BigDecimal total;

    private Integer isDelete;

    private String username;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
