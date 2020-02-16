package com.minjie.repository;

import com.minjie.dataobject.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by zhengjie on 2019/12/26.
 */
public interface BuyerLoginRepository extends JpaRepository<UserInfo,String> {
    UserInfo findByUsername(String name);
}
