package com.aqua.common.service.impl;

import com.aqua.common.exception.BusinessException;
import com.aqua.common.mapper.UserMapper;
import com.aqua.common.model.dto.LoginUserRequest;
import com.aqua.common.model.dto.RegisterUserRequest;
import com.aqua.common.model.dto.UserRequest;
import com.aqua.common.model.entity.User;
import com.aqua.common.model.vo.UserVO;
import com.aqua.common.service.UserService;
import com.aqua.common.utils.BaseResponse;
import com.aqua.common.utils.ErrorCode;
import com.aqua.common.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author water king
 * @time 2023/6/14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public BaseResponse<UserVO> selectOne(Long id) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            User user = userMapper.selectOne(queryWrapper);
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return Result.success(userVO);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "select error");
        }
    }

    @Override
    public BaseResponse<List<UserVO>> selectAll() {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("is_delete", 1);
            List<User> users = userMapper.selectList(queryWrapper);
            List<UserVO> userVOS = new ArrayList<>();
            for (User user : users) {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                userVOS.add(userVO);
            }
            return Result.success(userVOS);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "select error2");
        }
    }

    @Override
    public BaseResponse<Boolean> deleteById(Long id) {
        int i = userMapper.deleteById(id);
        if (i < 0) {
            // delete error
            return Result.error(ErrorCode.OPERATION_ERROR);
        } else {
            return Result.success(true);
        }
    }

    @Override
    public BaseResponse<UserVO> addUser(UserRequest userRequest) {
        try {
            User user = new User();
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userRequest,userVO);
            BeanUtils.copyProperties(userRequest, user);
            int insert = userMapper.insert(user);
            if (insert < 0) {
                //insert error
                return Result.error(ErrorCode.PARAMS_ERROR);
            } else {
                return Result.success(userVO);
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    @Override
    public BaseResponse<UserVO> updateUser(UserRequest userRequest) {
        try {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", userRequest.getId());
            User user = new User();
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(userRequest, userVO);
            BeanUtils.copyProperties(userRequest, user);
            int update = userMapper.update(user, queryWrapper);

            if (update < 0) {
                // update error
                return Result.error(ErrorCode.OPERATION_ERROR);
            } else {
                return Result.success(userVO);
            }
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    @Override
    public BaseResponse<UserVO> login(LoginUserRequest loginUserRequest) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", loginUserRequest.getUsername());

        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            return Result.success(userVO);
        } else {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    @Override
    public BaseResponse<UserVO> register(RegisterUserRequest request) {
        try {
            User user = new User();
            BeanUtils.copyProperties(request, user);
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", user.getUsername());
            User isExist = userMapper.selectOne(queryWrapper);
            if (isExist == null) {
                userMapper.insert(user);

                User user1 = userMapper.selectOne(queryWrapper);
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user1, userVO);
                return  Result.success(userVO);
            } else {
                return Result.error(ErrorCode.OPERATION_ERROR, "用户已存在");
            }


        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }
}
