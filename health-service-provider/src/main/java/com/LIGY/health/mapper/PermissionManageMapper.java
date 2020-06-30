package com.LIGY.health.mapper;

import com.LIGY.pojo.Permission;
import com.LIGY.pojo.Role;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

public interface PermissionManageMapper {


    //分页查询
    public Page<Role> selectByCondition(String queryString);


    void add(Permission permission);

    public List<Permission> findAll();

    //根据id删除会员
    void delete(Integer id);

    Permission  findById(Integer id);

    //删除掉原有的中间表关系
    void deleteAssociation(Integer id);


    //建立中间表关系
    void setPermission(Map map);

    //提交功能
    public List<Integer> findPermissionIdsByRoleIds(Integer id);

}
