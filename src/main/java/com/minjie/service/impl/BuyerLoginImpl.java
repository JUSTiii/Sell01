package com.minjie.service.impl;


import com.minjie.dataobject.UserInfo;
import com.minjie.repository.BuyerLoginRepository;
import com.minjie.service.BuyerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by zhengjie on 2019/12/26.
 */
@Service
public class BuyerLoginImpl implements BuyerLoginService {
    @Autowired
    private BuyerLoginRepository buyerLoginRepository;


    @Override
    public String buyerLogin(String username, String password) {
        UserInfo userInfo=buyerLoginRepository.findByUsername(username);
        //如果没有查到
        if (userInfo==null){
            return "此用户不存在";
        }else {
            if(userInfo.getPassword().equals(password)){
                return "登录成功";
            }else {
                return "用户名与密码不匹配";
            }

        }
    }

    @Override
    public String buyerRegister(UserInfo userInfo) {
        UserInfo userInfo1=buyerLoginRepository.findByUsername(userInfo.getUsername());
        if(userInfo1==null){
            return "用户名已经存在";
        }else {
            buyerLoginRepository.save(userInfo);
            return "注册成功,跳转到登录界面";
        }

    }
}
