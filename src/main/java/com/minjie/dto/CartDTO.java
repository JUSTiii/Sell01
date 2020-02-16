package com.minjie.dto;

import lombok.Data;


/**
 * Create by zj on 2019/8/1
 */
@Data
public class CartDTO {

    /*商品Id*/
    private String productId;

    /*数量*/
    private  Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "{" +
                "productId="+'\"' + productId + '\"' +
                ", productQuantity=" + productQuantity +
                '}';
    }

    public CartDTO() {

    }
}
