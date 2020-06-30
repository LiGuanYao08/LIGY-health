package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.User;
import com.LIGY.pojo.Role;

import java.util.List;

public interface UserManageService {

    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    List<Role> findAllRole();
    int addUser(User user);
    void addRole2User(Integer[] ids, Integer userId);

    void delUser(Integer userId);
    //新增功能
    public void add(User user);

    //数据回显
    public List<Integer> findRoleIdsByUserIds(Integer id);

    //编辑提交
    public void edit( Integer id,Integer[] roleIds);
}
