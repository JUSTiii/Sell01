package com.minjie.converter;

import com.google.gson.Gson;
import com.minjie.dto.CartDTO;

import java.util.List;

/**
 * Created by zhengjie on 2020/2/13.
 */
public class CartDTO2Json {

    public static String convert(List<CartDTO> cartDTOList){
        Gson gson=new Gson();
        String cartJson=gson.toJson(cartDTOList);
        return cartJson;
    }
}
