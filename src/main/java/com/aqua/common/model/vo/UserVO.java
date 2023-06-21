package com.aqua.common.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/14
 */
@Data
public class UserVO implements Serializable {

    private static final long serialVersionUID = 5L;

    private Long id;

    private String username;

    private String avator;

}
