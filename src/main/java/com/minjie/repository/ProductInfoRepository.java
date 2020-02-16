package com.minjie.repository;

import com.minjie.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Create by zj on 2018/6/29
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>, JpaSpecificationExecutor<ProductInfo>, PagingAndSortingRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

    Page<ProductInfo> findProductInfoByCategoryTypeAndProductStatus(Integer categoryType,Integer productStatus,Pageable pageable);
}
