package com.minjie.service.impl;

import com.minjie.dataobject.ProductInfo;
import com.minjie.dto.CartDTO;
import com.minjie.enums.ProductStatusEnum;
import com.minjie.enums.ResultEnum;
import com.minjie.exception.SellException;
import com.minjie.repository.ProductInfoRepository;
import com.minjie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Create by zj on 2018/6/29
 */

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findOne(String productId) {
        return repository.findOne(productId);
    }



    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.Up.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }


    @Override
    public Page<ProductInfo> findByCategoryTypeAndProductStatus(Integer categoryType,Integer productStatus,Pageable pageable) {
        return repository.findProductInfoByCategoryTypeAndProductStatus(categoryType,productStatus,pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO:cartDTOList){
            ProductInfo productInfo=repository.findOne(cartDTO.getProductId());
            if(productInfo==null){
                throw  new SellException((ResultEnum.PRODUCT_NOT_EXIST));
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
     for(CartDTO cartDTO:cartDTOList){
         ProductInfo productInfo=repository.findOne(cartDTO.getProductId());
         if(productInfo==null){
             throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
         }
         Integer result=productInfo.getProductStock()-cartDTO.getProductQuantity();
         if(result<0){
             throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
         }
         productInfo.setProductStock(result);
         repository.save(productInfo);
     }
    }

    @Override
    public ProductInfo onSale(String productId) {
        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.Up){
            throw  new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.Up.getCode());
        return repository.save(productInfo);
    }

    @Override
    public ProductInfo offSale(String productId) {
        ProductInfo productInfo=repository.findOne(productId);
        if(productInfo==null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        if(productInfo.getProductStatusEnum()==ProductStatusEnum.DOWN){
            throw  new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
        }
        //更新
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        return repository.save(productInfo);
    }
}
