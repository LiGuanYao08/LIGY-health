package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.Role;

import java.util.List;

public interface RoleManageService {


    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    //数据回显
    public List<Integer> findPermissionIdsByRoleIds(Integer id);

    //新增功能
    public void add(Role role);

    //查询所有角色
    public List<Role> findAll();

    public void edit(Integer id , Integer[] permissionIds);
}
