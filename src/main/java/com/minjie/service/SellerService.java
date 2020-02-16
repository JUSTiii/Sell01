package com.minjie.service;

import com.minjie.dataobject.SellerInfo;

/**
 * Create by zj on 2019/8/26
 */
public interface SellerService   {

    /**
     * 通过openid查询卖家端信息
     * @param openid
     * @return
     */
    SellerInfo  findSellerInfoByOpenid(String openid);
}
