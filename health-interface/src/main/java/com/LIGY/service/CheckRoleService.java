package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.pojo.Role;

import java.util.Map;

public interface CheckRoleService {
    void add(Role role);

    PageResult pageQuery(QueryPageBean queryPageBean);

    void delete(Integer id);

    void edit(Integer[] permissionIds, Integer roleId);

    Map findAllPermission(Integer roleId);
}
