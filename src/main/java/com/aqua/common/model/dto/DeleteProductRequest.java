package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/15
 */
@Data
public class DeleteProductRequest implements Serializable {

    private static final Long serialVersionUID = 532634L;

    private Long id;
}
