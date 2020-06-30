package com.LIGY.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.User;
import com.LIGY.service.UserManageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user_manage")
public class User_ManageController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Reference
    private UserManageService userManageService;


    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = userManageService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }




    //新增功能
    @RequestMapping("/add")
    public Result add (@RequestBody User user){
        try {
            String password = user.getPassword();
            System.out.println(passwordEncoder);
            String encodes = passwordEncoder.encode(password);
            user.setPassword(encodes);
            userManageService.add(user);
        }catch (Exception e ){
            //新增失败
            return new Result(false, MessageConstant.ADD_ROLE_FAIL);
        }
        //新增成功
        return new Result(true,MessageConstant.ADD_ROLE_SUCCESS);
    }

    //数据的回显
    @RequestMapping("/findRoleIdsByUserIds")
    public Result findRoleIdsByUserIds(Integer id){
        try {
            List<Integer> roleIds = userManageService.findRoleIdsByUserIds(id);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }

    //编辑提交
    @RequestMapping("/edit")
    public Result edit(Integer id,Integer[] roleIds){
        try {
            userManageService.edit(id,roleIds);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_ROLE_FAIL);
        }
        return  new Result(true,MessageConstant.EDIT_ROLE_SUCCESS);
    }
}
