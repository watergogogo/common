package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/14
 */
@Data
public class RegisterUserRequest implements Serializable {

    private static final Long serialVersionUID = 7343567L;

    private String username;

    private String password;
}
