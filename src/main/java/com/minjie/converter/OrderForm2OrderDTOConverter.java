package com.minjie.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minjie.dataobject.OrderDetail;
import com.minjie.dto.OrderDTO;
import com.minjie.enums.ResultEnum;
import com.minjie.exception.SellException;
import com.minjie.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Create by zj on 2019/8/2
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
  public static OrderDTO convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();

        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setUserId(orderForm.getUserId());
        List<OrderDetail> orderDetailList=new ArrayList<>();
        try {
            orderDetailList=gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){
            log.error("【对象转换】错误, string={}",orderForm.getItems());
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);
        return  orderDTO;
    }

}
