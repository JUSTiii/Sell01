package com.minjie.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Create by zj on 2019/8/25
 */
@Data
@Entity
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;
}
