package com.aqua.common.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author water king
 * @time 2023/6/14
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private BigDecimal price;

    private String img;

    private Integer stock;

    private Integer isDelete;
}
