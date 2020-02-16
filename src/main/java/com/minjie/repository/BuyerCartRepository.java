package com.minjie.repository;

import com.minjie.dataobject.UserCart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by zhengjie on 2020/2/12.
 */
public interface BuyerCartRepository extends JpaRepository<UserCart,String> {
  UserCart findByUserIdAndProductId(String userId,String productId);

  Page<UserCart> findByUserId(String userId, Pageable pageable);

  @Modifying
  @Transactional
  void deleteByCartId(String cartId);
}
