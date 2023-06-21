package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/15
 */
@Data
public class SearchOrderRequest implements Serializable {

    private static final Long serialVersionUID = 765434L;

    private String username;
}
