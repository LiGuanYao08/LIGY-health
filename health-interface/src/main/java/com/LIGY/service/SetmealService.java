package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealService {
    public void add(Setmeal setmeal,Integer[] checkgroupIds);
    public PageResult pageQuery(Integer currentPage,Integer pageSize,String queryString);
    public List<Setmeal> findAll();
    public Setmeal findById(Integer id);
    List<Map<String,Object>> findSetmealCount();

    void editSetmealById(Setmeal setmeal);

    void delete(Integer id);


}
