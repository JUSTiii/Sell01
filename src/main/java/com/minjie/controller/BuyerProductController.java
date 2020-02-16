package com.minjie.controller;


import com.minjie.converter.CartDTO2Json;
import com.minjie.dataobject.ProductInfo;
import com.minjie.dto.CartDTO;
import com.minjie.enums.ProductStatusEnum;
import com.minjie.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Create by zj on 2018/7/6
 */

@RestController  //返回json格式用这个
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam("categoryType") Integer categoryType,
                             @RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size",defaultValue = "8") Integer size,
                             Map<String,Object> map){
        PageRequest request=new PageRequest(page-1,size);

        //1.查询所有上架商品
       Page<ProductInfo> productInfoList= productService.findByCategoryTypeAndProductStatus(categoryType,ProductStatusEnum.Up.getCode(),request);

 /*       //2.查询类目(一次性查询)
   //    List<Integer> categoryTypeList=new ArrayList<>();
       //传统方法
//        for(ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }
        //精简方法(JAVA 8,lambda)
        List<ProductCategory> productCategoryList= categoryService.findByCategoryType(categoryType);
        //3.数据拼装
        List<ProductInfoVO> productInfoVOList=new ArrayList<>();
        for(ProductCategory productCategory:productCategoryList){
            ProductVO productVO=new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());

            for(ProductInfo productInfo:productInfoList){
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())&&
                productInfo.getProductStatus().equals(ProductStatusEnum.Up.getCode())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    //productInfo属性值拷贝到后面一个
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }

        }*/
        map.put("productInfoList",productInfoList);
        map.put("currentPage",page);
        map.put("categoryType",categoryType);
        return new ModelAndView("/login/Index",map);
    }
    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("productId")String productId,
                               Map<String,Object> map){
        //查询单个物品详情
        ProductInfo productInfo=productService.findOne(productId);
        List<CartDTO> cartDTOList=new ArrayList<>();
        CartDTO cartDTO=new CartDTO();
        cartDTO.setProductId(productInfo.getProductId());
        cartDTO.setProductQuantity(1);
        cartDTOList.add(cartDTO);
        String items= CartDTO2Json.convert(cartDTOList);
        map.put("items",items);
        map.put("productInfo",productInfo);
        return  new ModelAndView("/login/BookInfo");
    }
}
