package com.aqua.common.service;

import com.aqua.common.model.dto.AddProductRequest;
import com.aqua.common.model.entity.Product;
import com.aqua.common.model.vo.ProductVO;
import com.aqua.common.utils.BaseResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author water king
 * @time 2023/6/14
 */
public interface ProductService extends IService<Product> {

    BaseResponse<List<ProductVO>> selectAll();

    BaseResponse<ProductVO> selectOneById(Long id);

    BaseResponse<Boolean> deleteById(Long id);

    BaseResponse<ProductVO> addProduct(AddProductRequest addProductRequest);

    BaseResponse<ProductVO> updateProduct(AddProductRequest addProductRequest);

    BaseResponse<List<ProductVO>> selectByName(String name);

}
