package com.minjie.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * Create by zj on 2019/7/31
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private  String detailId;

    //订单id
    private  String orderId;

    //商品id
    private  String productId;

    //商品名称
    private  String  productName;

    //商品单价
    private BigDecimal productPrice;

    //商品数量
    private Integer productQuantity;

    //商品小图
    private  String productIcon;
}
