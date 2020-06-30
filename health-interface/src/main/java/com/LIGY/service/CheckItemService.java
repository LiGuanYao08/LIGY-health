package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.CheckItem;


import java.util.List;

/**
 * 检查服务接口
 */
public interface CheckItemService {
    public void add (CheckItem checkItem);
    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    //编辑
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);
    //删除
    public void delete(Integer id);
    //查询所有
    public List<CheckItem> findAll();

}
