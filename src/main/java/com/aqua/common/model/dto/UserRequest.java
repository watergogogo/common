package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/14
 */
@Data
public class UserRequest implements Serializable {

    private static final long serialVersionUID = 6L;

    private Long id;

    private String username;

    private String password;

    private String avator;
}
