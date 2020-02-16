package com.minjie.service;

import com.minjie.dataobject.ProductInfo;
import com.minjie.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * Create by zj on 2018/6/29
 */
public interface ProductService {
    ProductInfo findOne(String productId);

    //查询所有在架商品列表
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);  //参数是用来分页的

    //分页查询每个类别上架商品
    Page<ProductInfo> findByCategoryTypeAndProductStatus(Integer categoryType,Integer productStatus,Pageable pageable);


    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);

    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);

    //上架
    ProductInfo onSale(String productId);

    //下架
    ProductInfo offSale(String productId);
}
