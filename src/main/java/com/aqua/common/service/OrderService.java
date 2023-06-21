package com.aqua.common.service;

import com.aqua.common.model.dto.AddOrderRequest;
import com.aqua.common.model.dto.ListDeleteOrderRequest;
import com.aqua.common.model.dto.SearchOrderRequest;
import com.aqua.common.model.dto.UpdateOrderRequest;
import com.aqua.common.model.entity.Order;
import com.aqua.common.model.vo.OrderVO;
import com.aqua.common.utils.BaseResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author water king
 * @time 2023/6/15
 */
public interface OrderService extends IService<Order> {

    BaseResponse<OrderVO> selectOne(Long id);

    BaseResponse<List<OrderVO>> selectAll();

    BaseResponse<OrderVO> update(UpdateOrderRequest updateOrderRequest);

    BaseResponse<OrderVO> addOrder(AddOrderRequest addOrderRequest);

    BaseResponse<Boolean> deleteOrder(Long id);

    BaseResponse<List<OrderVO>> selectOne(SearchOrderRequest searchOrderRequest);

    Boolean selectByName(String name);

    BaseResponse<Boolean> listDelete(ListDeleteOrderRequest listDeleteOrderRequest);
}
