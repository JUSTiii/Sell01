package com.minjie.repository;

import com.minjie.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Create by zj on 2019/7/31
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByUserId(String userid,Pageable pageable);
}
