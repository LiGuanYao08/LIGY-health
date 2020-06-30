package com.LIGY.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Role;
import com.LIGY.service.RoleManageService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role_manage")
public class Role_ManageController {

    @Reference
    private RoleManageService roleManageService;

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = roleManageService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }




    //新增功能
    @RequestMapping("/add")
    public Result add (@RequestBody Role role){
        try {
            roleManageService.add(role);
        }catch (Exception e ){
            //新增失败
            return new Result(false, MessageConstant.ADD_ROLE_FAIL);
        }
        //新增成功
        return new Result(true,MessageConstant.ADD_ROLE_SUCCESS);
    }


    //显示全部功能
    @RequestMapping("/findAll")
    public Result findAll (){
            List<Role> roleList = roleManageService.findAll();
            if (roleList != null && roleList.size()>0){
                Result result = new Result(true,MessageConstant.QUERY_ROLE_SUCCESS);
                result.setData(roleList);
                return result;
            }
            return new Result(false, MessageConstant.QUERY_ROLE_FAIL);
    }

    //数据的回显
    @RequestMapping("/findPermissionIdsByRoleIds")
    public Result findPermissionIdsByRoleIds(Integer id){
        try {
            List<Integer> permissionIds = roleManageService.findPermissionIdsByRoleIds(id);
            return new Result(true,MessageConstant.QUERY_PERMISSION_SUCCESS,permissionIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_PERMISSION_FAIL);
        }
    }


    //编辑提交
    @RequestMapping("/edit")
    public Result edit(Integer id,Integer[] permissionIds){
        try {
            roleManageService.edit(id,permissionIds);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_PERMISSION_FAIL);
        }
        return  new Result(true,MessageConstant.EDIT_PERMISSION_SUCCESS);
    }
}
