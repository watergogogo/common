package com.aqua.common.controller;

import com.aqua.common.model.dto.AddProductRequest;
import com.aqua.common.model.dto.DeleteProductRequest;
import com.aqua.common.model.dto.SearchProductRequest;
import com.aqua.common.model.vo.ProductVO;
import com.aqua.common.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/selectall")
    public BaseResponse<List<ProductVO>> selectAll() {
        BaseResponse<List<ProductVO>> listBaseResponse = productService.selectAll();
        return listBaseResponse;
    }

    @GetMapping("/selectone/{id}")
    public BaseResponse<ProductVO> selectOneById(@PathVariable Long id) {
        BaseResponse<ProductVO> productVOBaseResponse = productService.selectOneById(id);
        return productVOBaseResponse;
    }

    @DeleteMapping("/delete")
    public BaseResponse<Boolean> deleteById(@RequestBody DeleteProductRequest deleteProductRequest) {
        BaseResponse<Boolean> booleanBaseResponse = productService.deleteById(deleteProductRequest.getId());
        return booleanBaseResponse;
    }

    @PostMapping("/addproduct")
    public BaseResponse<ProductVO> addProduct(@RequestBody AddProductRequest addProductRequest) {
        BaseResponse<ProductVO> productVOBaseResponse = productService.addProduct(addProductRequest);
        return productVOBaseResponse;
    }

    @PutMapping("/updateproduct")
    public BaseResponse<ProductVO> updateProduct(@RequestBody AddProductRequest addProductRequest) {
        BaseResponse<ProductVO> productVOBaseResponse = productService.updateProduct(addProductRequest);
        return productVOBaseResponse;
    }

    @PostMapping("/selectbyname")
    public BaseResponse<List<ProductVO>> selectByName(@RequestBody SearchProductRequest searchProductRequest) {
        BaseResponse<List<ProductVO>> listBaseResponse = productService.selectByName(searchProductRequest.getName());
        return listBaseResponse;
    }
}
