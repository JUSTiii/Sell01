package com.minjie.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by zhengjie on 2019/12/30.
 */
@Data
public class UserForm {
    //买家姓名
    @NotEmpty(message = "姓名必填")
    private String username;

    //买家密码
    @NotEmpty(message = "密码必填")
    private String password;

    //性别
    @NotEmpty(message = "性别必选")
    private  String gender;

    //买家手机号
    @NotEmpty(message = "手机号必填")
    private  String telephone;

    //买家地址
    @NotEmpty(message = "地址必填")
    private  String address;

    //买家email
    @NotEmpty(message = "email必填")
    private  String email;


}
