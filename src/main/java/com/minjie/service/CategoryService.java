package com.minjie.service;

import com.minjie.dataobject.ProductCategory;

import java.util.List;


//类目
public interface CategoryService {
    ProductCategory findOne(Integer categoryID);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryType(Integer categoryType);

    ProductCategory save(ProductCategory productCategory);

}
