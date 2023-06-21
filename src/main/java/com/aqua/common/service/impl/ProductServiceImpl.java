package com.aqua.common.service.impl;

import com.aqua.common.exception.BusinessException;
import com.aqua.common.mapper.ProductMapper;
import com.aqua.common.model.dto.AddProductRequest;
import com.aqua.common.model.entity.Product;
import com.aqua.common.model.vo.ProductVO;
import com.aqua.common.service.ProductService;
import com.aqua.common.utils.BaseResponse;
import com.aqua.common.utils.ErrorCode;
import com.aqua.common.utils.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author water king
 * @time 2023/6/14
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {
    
    @Resource
    private ProductMapper productMapper;
    
    @Override
    public BaseResponse<List<ProductVO>> selectAll() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.ne("is_delete", 1);
        return getListBaseResponse(queryWrapper);
    }

    @Override
    public BaseResponse<ProductVO> selectOneById(Long id) {
        try {
            Product product = productMapper.selectById(id);
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            return Result.success(productVO);
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "select error" + e);
        }
    }

    @Override
    public BaseResponse<Boolean> deleteById(Long id) {
        int i = productMapper.deleteById(id);
        if (i < 0) {
            //delete error
            return Result.error(ErrorCode.PARAMS_ERROR);
        } else {
            return Result.success(true);
        }
    }

    @Override
    public BaseResponse<ProductVO> addProduct(AddProductRequest addProductRequest) {

        Product product = new Product();
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(addProductRequest, product);
        BeanUtils.copyProperties(addProductRequest, productVO);
        int insert = productMapper.insert(product);
        if (insert < 0) {
            //insert error
            return Result.error(ErrorCode.OPERATION_ERROR, "insert error");
        } else {
            return Result.success(productVO);
        }
    }

    @Override
    public BaseResponse<ProductVO> updateProduct(AddProductRequest addProductRequest) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", addProductRequest.getId());

        Product product = new Product();
        ProductVO productVO = new ProductVO();
        BeanUtils.copyProperties(addProductRequest, product);
        BeanUtils.copyProperties(addProductRequest, productVO);
        int update = productMapper.update(product, queryWrapper);

        if (update < 0) {
            //update error
            return Result.error(ErrorCode.OPERATION_ERROR, "update error");
        } else {
            return Result.success(productVO);
        }
    }

    @Override
    public BaseResponse<List<ProductVO>> selectByName(String name) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", name);
        return getListBaseResponse(queryWrapper);
    }

    @NotNull
    private BaseResponse<List<ProductVO>> getListBaseResponse(QueryWrapper<Product> queryWrapper) {
        List<Product> products = productMapper.selectList(queryWrapper);
        List<ProductVO> productVOS = new ArrayList<>();
        for (Product product : products) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOS.add(productVO);
        }
        return Result.success(productVOS);
    }
}