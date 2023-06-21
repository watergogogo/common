package com.aqua.common.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author water king
 * @time 2023/6/15
 */
@Data
public class OrderVO implements Serializable {

    private static final Long serialVersionUID = 76543L;

    private Long id;

    private String name;

    private BigDecimal price;

    private int count;

    private BigDecimal total;

    private String img;

}
