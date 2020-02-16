package com.minjie.service;

import com.minjie.dataobject.UserInfo;

/**
 * Created by zhengjie on 2019/12/26.
 */
public interface BuyerLoginService {

    String buyerLogin(String username,String password);

    String buyerRegister(UserInfo userInfo);

}
