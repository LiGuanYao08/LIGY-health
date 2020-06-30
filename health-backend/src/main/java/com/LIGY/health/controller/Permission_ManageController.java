package com.LIGY.health.controller;

import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Permission;
import com.LIGY.service.PermissionManageService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
public class Permission_ManageController {

    @Reference
    private PermissionManageService permissionManageService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = permissionManageService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }




    //新增功能
    @RequestMapping("/add")
    public Result add (@RequestBody Permission permission){
        try {
            permissionManageService.add(permission);
        }catch (Exception e ){
            //新增失败
            return new Result(false, MessageConstant.ADD_PERMISSION_FAIL);
        }
        //新增成功
        return new Result(true,MessageConstant.ADD_PERMISSION_SUCCESS);
    }

    //显示全部功能
    @RequestMapping("/findAll")
    public Result findAll (){
        List<Permission> roleList = permissionManageService.findAll();
        if (roleList != null && roleList.size()>0){
            Result result = new Result(true,MessageConstant.QUERY_ROLE_SUCCESS);
            result.setData(roleList);
            return result;
        }
        return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
    }


    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            permissionManageService.delete(id);
            return new Result(true,MessageConstant.DELETE_PERMISSION_SUCCESS);
        }catch (Exception E){
            E.printStackTrace();
            return new Result(false,MessageConstant.DELETE_PERMISSION_FAIL);
        }

    }

    @RequestMapping("/findById")
    public Result queryById(Integer id){
        try {
            Permission permission=permissionManageService.findById(id);
            return new Result(true,MessageConstant.FIND_PERMISSION_SUCCESS);
        }catch (Exception E){
            return new Result(false,MessageConstant.FIND_PERMISSION_FAIL);
        }



    }



    //编辑提交
    @RequestMapping("/edit")
    public Result edit(Integer id,Integer[] permissionIds){
        try {
            permissionManageService.edit(id,permissionIds);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_PERMISSION_FAIL);
        }
        return  new Result(true,MessageConstant.EDIT_PERMISSION_SUCCESS);
    }

    //数据的回显
    @RequestMapping("/findPermissionIdsByRoleIds")
    public Result findPermissionIdsByRoleIds(Integer id){
        try {
            List<Integer> roleIds = permissionManageService.findPermissionIdsByRoleIds(id);
            return new Result(true,MessageConstant.QUERY_ROLE_SUCCESS,roleIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ROLE_FAIL);
        }
    }
}
