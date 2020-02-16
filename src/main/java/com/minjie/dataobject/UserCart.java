package com.minjie.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhengjie on 2020/2/12.
 */
@Data
@Entity
@DynamicUpdate
public class UserCart {

    @Id
    private String cartId;

    private String userId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productQuantity;

    private String productIcon;

    private Date createTime;

    private Date updateTime;

}
