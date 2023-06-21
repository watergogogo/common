package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author water king
 * @time 2023/6/15
 */
@Data
public class AddOrderRequest implements Serializable {

    private static final Long serialVersionUID = 76543644L;

    private String name;

    private String img;

    private BigDecimal price;

    private int count;

    private BigDecimal total;

    private String username;
}
