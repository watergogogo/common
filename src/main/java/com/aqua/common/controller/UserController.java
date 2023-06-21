package com.aqua.common.controller;

import com.aqua.common.model.dto.LoginUserRequest;
import com.aqua.common.model.dto.RegisterUserRequest;
import com.aqua.common.model.dto.UserRequest;
import com.aqua.common.model.vo.UserVO;
import com.aqua.common.service.UserService;
import com.aqua.common.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author water king
 * @time 2023/6/14
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/select/{id}")
    public BaseResponse<UserVO> selectOneById(@PathVariable Long id) {
        BaseResponse<UserVO> userVOBaseResponse = userService.selectOne(id);
        return userVOBaseResponse;
    }

    @GetMapping("/select")
    public BaseResponse<List<UserVO>> selectAll() {
        BaseResponse<List<UserVO>> listBaseResponse = userService.selectAll();
        return listBaseResponse;
    }

    @PostMapping("/add")
    public BaseResponse<UserVO> addUser(@RequestBody UserRequest userRequest) {
        BaseResponse<UserVO> userVOBaseResponse = userService.addUser(userRequest);
        return userVOBaseResponse;
    }

    @PutMapping("/update")
    public BaseResponse<UserVO> updateUser(@RequestBody UserRequest userRequest) {
        BaseResponse<UserVO> userVOBaseResponse = userService.updateUser(userRequest);
        return userVOBaseResponse;
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Boolean> delete(@PathVariable Long id) {
        BaseResponse<Boolean> booleanBaseResponse = userService.deleteById(id);
        return booleanBaseResponse;
    }

    @PostMapping("/login")
    public BaseResponse<UserVO> login(@RequestBody LoginUserRequest loginUserRequest) {
        BaseResponse<UserVO> userVOBaseResponse = userService.login(loginUserRequest);
        return userVOBaseResponse;
    }

    @PostMapping("/register")
    public BaseResponse<UserVO> register(@RequestBody RegisterUserRequest request) {
        BaseResponse<UserVO> register = userService.register(request);
        return register;
    }
}
