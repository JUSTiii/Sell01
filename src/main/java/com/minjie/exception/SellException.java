package com.minjie.exception;

import com.minjie.enums.ResultEnum;

/**
 * Create by zj on 2019/8/1
 */
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();
    }
    public SellException(Integer code,String messsage) {
        super(messsage);

        this.code=code;
    }
}
