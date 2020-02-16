package com.minjie.service.impl;

import com.minjie.dataobject.ProductCategory;
import com.minjie.repository.ProductCategoryRepository;
import com.minjie.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//类目
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private ProductCategoryRepository repository;
    @Override
    public ProductCategory findOne(Integer categoryID) {
        return repository.findOne(categoryID);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryType(Integer categoryType) {
        return repository.findByCategoryType(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}
