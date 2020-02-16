package com.minjie.controller;

import com.minjie.converter.CartDTO2Json;
import com.minjie.dataobject.ProductInfo;
import com.minjie.dataobject.UserCart;
import com.minjie.dataobject.UserInfo;
import com.minjie.dto.CartDTO;
import com.minjie.service.BuyerService;
import com.minjie.service.ProductService;
import com.minjie.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhengjie on 2020/2/12.
 */

@RestController
@RequestMapping("/buyer/cart")
public class BuyerCartController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BuyerService buyerService;



    @GetMapping("/insert")
    public ModelAndView insert(@RequestParam("productId") String productId,
                               @RequestParam("userId") String userId,
                               Map<String,Object> map){

        UserCart userCart=buyerService.findByUserIdAndProductId(userId,productId);

        //查询单个物品详情
        ProductInfo productInfo=productService.findOne(productId);
        //购物车入库
        if(userCart==null) {
            userCart=new UserCart();
            userCart.setCartId(KeyUtil.genUniqueKey());
            userCart.setUserId(userId);
            userCart.setProductId(productInfo.getProductId());
            userCart.setProductName(productInfo.getProductName());
            userCart.setProductPrice(productInfo.getProductPrice());
            userCart.setProductQuantity(1);
            userCart.setProductIcon(productInfo.getProductIcon());
        }else  {
            userCart.setProductQuantity(userCart.getProductQuantity()+1);
        }
        buyerService.insertCart(userCart);
        map.put("msg","加入购物车成功");
        map.put("productInfo",productInfo);
        return new ModelAndView("/login/BookInfo",map);
    }

    @GetMapping("/list")
    public ModelAndView list( @RequestParam("userId") String userId,
                              @RequestParam(value = "page",defaultValue = "1") Integer page,
                              @RequestParam(value = "size",defaultValue = "5") Integer size,
                               Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);
        BigDecimal cartAmount=new BigDecimal(BigInteger.ZERO);
        //查询对应用户购物车
        Page<UserCart> userCartList=buyerService.findByUserId(userId,request);
        List<CartDTO> cartDTOList=new ArrayList<>();
        for(UserCart userCart:userCartList.getContent()){
            CartDTO cartDTO=new CartDTO();
            cartDTO.setProductId(userCart.getProductId());
            cartDTO.setProductQuantity(userCart.getProductQuantity());
            cartDTOList.add(cartDTO);
            cartAmount=userCart.getProductPrice().multiply(new BigDecimal(userCart.getProductQuantity())).add(cartAmount);
        }
        String items= CartDTO2Json.convert(cartDTOList);
        Long totalNums=userCartList.getTotalElements();
        map.put("items",items);
        map.put("userCartList",userCartList);
        map.put("currentPage",page);
        map.put("cartAmount",cartAmount);
        map.put("totalNums",totalNums);
        return new ModelAndView("login/Cart",map);
    }

    @GetMapping("/delete")
    public ModelAndView delete(@RequestParam("cartId") String cartId,
                               HttpSession session,
                               Map<String,Object> map){
        buyerService.delete(cartId);
        UserInfo userInfo= (UserInfo) session.getAttribute("user");

        String url="/sell/buyer/cart/list?userId="+userInfo.getUserId();
        map.put("msg","删除成功");
        map.put("url",url);
        return new ModelAndView("common/success",map);
    }

}
