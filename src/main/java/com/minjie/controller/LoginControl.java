package com.minjie.controller;

import com.minjie.dataobject.UserInfo;
import com.minjie.form.UserForm;
import com.minjie.repository.BuyerLoginRepository;
import com.minjie.service.BuyerLoginService;
import com.minjie.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by zhengjie on 2019/12/25.
 */
@Controller
@RequestMapping("/seller/login")
public class LoginControl {
    private String url="/sell/buyer/product/list?categoryType=1";
    @Autowired
    private BuyerLoginService buyerLoginService;
    @Autowired
    private BuyerLoginRepository buyerLoginRepository;


    @RequestMapping("/start")
    public ModelAndView start(Map<String,Object> map){
        map.put("msg","");
        return new ModelAndView("login/Login");
    }

    @RequestMapping("/login")
    public ModelAndView login(@RequestParam(value = "username", required = false) String username,
                              @RequestParam(value = "password", required = false) String password,
                              HttpSession session,
                              Map<String, Object> map) {

        String s = buyerLoginService.buyerLogin(username, password);
        if(s.equals("登录成功")){
            UserInfo userInfo=buyerLoginRepository.findByUsername(username);
            session.setAttribute("user",userInfo);
            map.put("msg", s);
            map.put("url",url);
            return new ModelAndView("common/success", map);
        }else {
            map.put("msg",s);
            return new ModelAndView("login/Login");
        }

    }

    @RequestMapping("/loginOut")
    public ModelAndView loginOut(HttpSession session){
         session.invalidate();
        return new ModelAndView("login/Login");
    }

    @PostMapping("/register")
    public ModelAndView register(@Valid UserForm userForm, BindingResult bindingResult, Map<String, Object> map) {
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userForm, userInfo);
        String userId= KeyUtil.genUniqueKey();
        userInfo.setUserId(userId);
        String msg = buyerLoginService.buyerRegister(userInfo);
        if (bindingResult.hasErrors()) {
            map.put("msg", "请正确填写信息");
            return new ModelAndView("login/Register");
        } else if (msg.equals("用户名已经存在")) {
            map.put("msg", msg);
            return new ModelAndView("login/Register");
        } else {
            map.put("msg", msg);
            map.put("url","/sell/seller/start");
            return new ModelAndView("common/success");
        }

    }

    @RequestMapping("/registerInit")
    public String register() {
        return "login/Register";
    }
    @RequestMapping("/demo")
    public String order()    {
        return "login/Cart";
    }

    @RequestMapping("/demo1")
    public String orderInfo() {
        return "login/UserInfo";
    }
    @RequestMapping("/bookInfo")
    public String bookInfo() {
        return "login/bookInfo";
    }

}
