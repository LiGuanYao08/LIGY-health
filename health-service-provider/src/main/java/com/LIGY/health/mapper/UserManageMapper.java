package com.LIGY.health.mapper;

import com.github.pagehelper.Page;

import com.LIGY.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserManageMapper {


    //分页查询
    public Page<User> selectByCondition(String queryString);



    //新增用户
    void add(User user);

    //建立中间表关系
    void setUserAndRole(Map map);

    //提交功能
    public List<Integer> findRoleIdsByUserIds(Integer id);

    //删除掉原有的中间表关系
    void deleteAssociation(Integer id);


}
