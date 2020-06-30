package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.Permission;

import java.util.List;

public interface PermissionManageService {

    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    //查询所有权限
    public List<Permission> findAll();
    //新增权限
    public void add(Permission permission);

    public void delete(Integer id);

    Permission findById(Integer id);

    public void edit(Integer id , Integer[] permissionIds);
    //数据回显
    public List<Integer> findPermissionIdsByRoleIds(Integer id);

}
