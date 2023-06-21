package com.aqua.common.controller;

import com.aqua.common.model.dto.*;
import com.aqua.common.model.vo.OrderVO;
import com.aqua.common.service.OrderService;
import com.aqua.common.utils.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author water king
 * @time 2023/6/15
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/select")
    public BaseResponse<List<OrderVO>> selectAll() {
        BaseResponse<List<OrderVO>> listBaseResponse = orderService.selectAll();
        return listBaseResponse;
    }

    @GetMapping("/select/{id}")
    public BaseResponse<OrderVO> selectOne(@PathVariable Long id) {
        BaseResponse<OrderVO> orderVOBaseResponse = orderService.selectOne(id);
        return orderVOBaseResponse;
    }

    @PostMapping("/selectbyname")
    public BaseResponse<List<OrderVO>> selectByName(@RequestBody SearchOrderRequest searchOrderRequest) {
        BaseResponse<List<OrderVO>> listBaseResponse = orderService.selectOne(searchOrderRequest);
        return listBaseResponse;
    }

    @DeleteMapping("/delete")
    public BaseResponse<Boolean> delete(@RequestBody DeleteOrderRequest deleteOrderRequest) {
        System.out.println(deleteOrderRequest.getId() + "order controller");
        BaseResponse<Boolean> booleanBaseResponse = orderService.deleteOrder(deleteOrderRequest.getId());
        return booleanBaseResponse;
    }

    @PutMapping("/update")
    public BaseResponse<OrderVO> update(@RequestBody UpdateOrderRequest updateOrderRequest) {
        BaseResponse<OrderVO> update = orderService.update(updateOrderRequest);
        return update;
    }

    @PostMapping("/add")
    public BaseResponse<OrderVO> addOrder(@RequestBody AddOrderRequest addOrderRequest) {
        BaseResponse<OrderVO> orderVOBaseResponse = orderService.addOrder(addOrderRequest);
        return orderVOBaseResponse;
    }

    @PostMapping("/listdelete")
    public BaseResponse<Boolean> listDelete(@RequestBody ListDeleteOrderRequest listDeleteOrderRequest) {
        BaseResponse<Boolean> booleanBaseResponse = orderService.listDelete(listDeleteOrderRequest);
        return booleanBaseResponse;
    }
}
