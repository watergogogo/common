package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/15
 */
@Data
public class DeleteOrderRequest implements Serializable {

    private static final Long serialVersionUID = 87657654L;

    private Long id;
}
