package com.minjie.controller;

import com.minjie.VO.ResultVO;
import com.minjie.converter.OrderForm2OrderDTOConverter;
import com.minjie.dataobject.UserInfo;
import com.minjie.dto.OrderDTO;
import com.minjie.enums.ResultEnum;
import com.minjie.exception.SellException;
import com.minjie.form.OrderForm;
import com.minjie.service.BuyerService;
import com.minjie.service.OrderService;
import com.minjie.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create by zj on 2019/8/2
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单  @Valid 表示要验证后面这个对象  验证结果返回到BindingResult bindingResult 对象里
    @PostMapping("/create")
    public ModelAndView create(@Valid OrderForm orderForm, BindingResult bindingResult, Map<String,Object> map){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确, orderForm={}",orderForm);
            throw  new SellException(ResultEnum.PARAM_ERROR.getCode(),
                                     bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO= OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw  new SellException(ResultEnum.CART_EMPTY);
        }
        OrderDTO createResult=orderService.create(orderDTO);
        String url="/sell/buyer/product/list?categoryType=1";
        map.put("msg","购买成功,请继续购物");
        map.put("url",url);
       /* Map<String,String > map=new HashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVOUtil.success(map);*/
       return new ModelAndView("common/success",map);

    }
    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if(StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw  new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,request);

        return ResultVOUtil.success(orderDTOPage.getContent());
    }
    //订单详情
     @GetMapping("/detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderId){

         OrderDTO orderDTO=buyerService.findOrderOne(openid,orderId);
         return ResultVOUtil.success(orderDTO);
     }
    //取消订单
    @PostMapping("/cancel")
    public  ResultVO cancel(@RequestParam("userId") String userId,
                            @RequestParam("orderId") String orderId){

        buyerService.cancelOrder(userId,orderId);
        return  ResultVOUtil.success();
    }

}
