package com.LIGY.health.controller;

import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.Result;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//用户操作
@RestController
@RequestMapping("/user")
public class UserController {
    //获得当前登录用户的用户名
    @RequestMapping("/getUserName")
    public Result getUserName(){
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return new Result(true, MessageConstant.GET_USERNAME_SUCCESS,userDetails.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_USERNAME_FAIL);
        }
    }
}
