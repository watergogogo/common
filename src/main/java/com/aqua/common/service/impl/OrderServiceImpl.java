package com.aqua.common.service.impl;

import com.aqua.common.exception.BusinessException;
import com.aqua.common.mapper.OrderMapper;
import com.aqua.common.model.dto.AddOrderRequest;
import com.aqua.common.model.dto.ListDeleteOrderRequest;
import com.aqua.common.model.dto.SearchOrderRequest;
import com.aqua.common.model.dto.UpdateOrderRequest;
import com.aqua.common.model.entity.Order;
import com.aqua.common.model.vo.OrderVO;
import com.aqua.common.service.OrderService;
import com.aqua.common.utils.BaseResponse;
import com.aqua.common.utils.ErrorCode;
import com.aqua.common.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author water king
 * @time 2023/6/15
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public BaseResponse<OrderVO> selectOne(Long id) {
        try {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("id", id);
            Order order = orderMapper.selectOne(queryWrapper);
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order, orderVO);
            return Result.success(orderVO);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    @Override
    public BaseResponse<List<OrderVO>> selectAll() {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.ne("is_delete", 1);
            List<Order> orders = orderMapper.selectList(queryWrapper);
            List<OrderVO> orderVOList = new ArrayList<>();
            for (Order order : orders) {
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(order, orderVO);
                orderVOList.add(orderVO);
            }
            return Result.success(orderVOList);
    }

    @Override
    public BaseResponse<OrderVO> update(UpdateOrderRequest updateOrderRequest) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", updateOrderRequest.getId());
        Order order = new Order();
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(updateOrderRequest, orderVO);
        BeanUtils.copyProperties(updateOrderRequest, order);
        int update = orderMapper.update(order, queryWrapper);
        if (update < 0) {
            // update error
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        } else {
            return Result.success(orderVO);
        }
    }

    @Override
    public BaseResponse<OrderVO> addOrder(AddOrderRequest addOrderRequest) {
        Order order = new Order();
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(addOrderRequest, orderVO);
        BeanUtils.copyProperties(addOrderRequest, order);

        if (selectByName(addOrderRequest.getName())) {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", addOrderRequest.getName());

            Order order1 = orderMapper.selectOne(queryWrapper);
            order1.setCount(order1.getCount() + 1);

            int update = orderMapper.update(order1, queryWrapper);
            orderVO.setCount(order1.getCount());
            if (update < 0) {
                //error
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            } else {
                return Result.success(orderVO);
            }
        } else {

            int insert = orderMapper.insert(order);
            if (insert < 0) {
                //insert error
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            } else {
                return Result.success(orderVO);
            }
        }
    }

    @Override
    public BaseResponse<Boolean> deleteOrder(Long id) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        int delete = orderMapper.delete(queryWrapper);
        if (delete < 0) {
            // delete error
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        } else {
            return Result.success(true);
        }
    }

    @Override
    public BaseResponse<List<OrderVO>> selectOne(SearchOrderRequest searchOrderRequest) {
        try {
            QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", searchOrderRequest.getUsername());
            List<Order> orders = orderMapper.selectList(queryWrapper);
            List<OrderVO> list = new ArrayList<>();
            for (Order order : orders) {
                OrderVO orderVO = new OrderVO();
                BeanUtils.copyProperties(order, orderVO);
                list.add(orderVO);
            }
            return Result.success(list);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
    }

    @Override
    public Boolean selectByName(String name) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        Order order = orderMapper.selectOne(queryWrapper);
        if (order != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public BaseResponse<Boolean> listDelete(ListDeleteOrderRequest listDeleteOrderRequest) {
        try {
            List<String> lists = listDeleteOrderRequest.getListOrderID();
            for (String list : lists) {
                QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("id", list);
                int delete = orderMapper.delete(queryWrapper);
            }
            return Result.success(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
