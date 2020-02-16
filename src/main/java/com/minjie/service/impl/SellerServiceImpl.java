package com.minjie.service.impl;

import com.minjie.dataobject.SellerInfo;
import com.minjie.repository.SellerInfoRepository;
import com.minjie.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create by zj on 2019/8/26
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;
    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);
    }
}
