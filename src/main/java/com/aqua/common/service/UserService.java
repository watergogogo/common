package com.aqua.common.service;

import com.aqua.common.model.dto.LoginUserRequest;
import com.aqua.common.model.dto.RegisterUserRequest;
import com.aqua.common.model.dto.UserRequest;
import com.aqua.common.model.entity.User;
import com.aqua.common.model.vo.UserVO;
import com.aqua.common.utils.BaseResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author water king
 * @time 2023/6/14
 */
public interface UserService extends IService<User> {

    BaseResponse<UserVO> selectOne(Long id);

    BaseResponse<List<UserVO>> selectAll();

    BaseResponse<Boolean> deleteById(Long id);

    BaseResponse<UserVO> addUser(UserRequest userRequest);

    BaseResponse<UserVO> updateUser(UserRequest userRequest);

    BaseResponse<UserVO> login(LoginUserRequest loginUserRequest);

    BaseResponse<UserVO> register(RegisterUserRequest request);
}
