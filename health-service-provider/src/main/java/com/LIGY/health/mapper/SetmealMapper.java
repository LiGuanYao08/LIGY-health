package com.LIGY.health.mapper;

import com.github.pagehelper.Page;
import com.LIGY.pojo.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealMapper {
    //新增套餐
    void add(Setmeal setmeal);
    //建立中间关系表
    void setSetmealAndCheckGroup(Map<String,Integer> map);
    //分页查询
    Page<Setmeal> selectByCondition(String queryString);
    //查询所有套餐
    List<Setmeal> findAll();
    //查询套餐详情
    Setmeal findById(Integer id);

    //查询套餐预约数量
    List<Map<String,Object>> findSetmealCount();
    //更新套餐数量
    void updateSetmeal(Setmeal one);
    //删除套餐
    void deleteSetmeal(Integer id);


}
