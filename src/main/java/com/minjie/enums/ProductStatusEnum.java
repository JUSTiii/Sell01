package com.minjie.enums;

import lombok.Getter;

/**
 * 商品状态
 * Create by zj on 2018/6/29
 */

@Getter
public enum ProductStatusEnum implements CodeEnum {
    Up(0,"在架"),
    DOWN(1,"下架"),
    ;
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message=message;
    }


}
