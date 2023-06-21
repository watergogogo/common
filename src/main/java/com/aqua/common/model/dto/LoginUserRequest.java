package com.aqua.common.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author water king
 * @time 2023/6/14
 */
@Data
public class LoginUserRequest implements Serializable {

    private static final Long serialVersionUID = 734567L;

    private String username;

    private String password;
}
