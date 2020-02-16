package com.minjie.repository;

import com.minjie.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by zj on 2019/8/25
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {
    SellerInfo findByOpenid(String openid);
}
