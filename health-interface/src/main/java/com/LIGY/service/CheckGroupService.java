package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {
    //新增检查组
    void add(CheckGroup checkGroup ,Integer[] checkitemIds);
    //分页显示
    public PageResult pageQuery(Integer currentPage,Integer pageSize,String queryString);
    //编辑功能
    public  CheckGroup findById(Integer id);
    //编辑功能中的检查项勾选回显
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id);
    //编辑提交
    public void edit(CheckGroup checkGroup,Integer[] checkitemIds);
    //查询所有组
    public List<CheckGroup> findAll();

    //删除
    public void delete(Integer id);

//      void deleteById(Integer id);
}
