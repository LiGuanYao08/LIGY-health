package com.LIGY.health.mapper;

import com.github.pagehelper.Page;
import com.LIGY.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleManageMapper {


    //分页查询
    public Page<Role> selectByCondition(String queryString);

    //数据的回显
    public List<Integer> findPermissionIdsByRoleIds(Integer id);

    //添加角色
    void add(Role role);

    //建立中间表关系
    void setRoleAndPermission(Map map);

    //查询所有角色信息
    public List<Role> findAll();

    //删除掉原有的中间表关系
    void deleteAssociation(Integer id);
}
