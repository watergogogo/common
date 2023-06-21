package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author water king
 * @time 2023/6/16
 */
@Data
public class ListDeleteOrderRequest implements Serializable {

    private static final Long serialVersionUID = 46566542L;

    private List<String> listOrderID;
}
