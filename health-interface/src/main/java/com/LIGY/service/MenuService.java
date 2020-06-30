package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Menu;

import java.util.List;

public interface MenuService {
    void add(Menu menu);

    List<Menu> findAll();

    List<Integer> findByRoleId(Integer id);

    PageResult findPage(QueryPageBean queryPageBean);

    void delById(Integer id);

    Menu findById(Integer id);

    void edit(Menu menu);

    List<Menu> findFu();

    Object findMenuByUserId(String username);

    Result addRoleAndMenu(Integer menuId, Integer[] roleIds);

    List<Integer> findAllRoleIds(Integer menuId);
}
