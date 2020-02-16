package com.minjie.service;

import com.minjie.dataobject.UserCart;
import com.minjie.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 买家
 * Create by zj on 2019/8/3
 */
public interface BuyerService {

    //查询该用户之前有没有加入该商品
    UserCart findByUserIdAndProductId(String userId, String productId);

    //加入购物车
    void insertCart(UserCart userCart);

    //展示购物车
    Page<UserCart> findByUserId(String userId, Pageable pageable);

    //删除购物车某件物品

    void delete(String cartId);

    //查询一个订单
    OrderDTO findOrderOne(String userId, String orderId);

    //取消订单
    OrderDTO cancelOrder(String userId, String orderId);


}
