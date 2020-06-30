package com.LIGY.health.mapper;

import com.LIGY.pojo.Order;

import java.util.List;
import java.util.Map;

public interface OrderMapper {
    //新增订单
    void add(Order order);
    //条件查询订单列表
    List<Order> findByCondition(Order order);
    //预约成功界面根据id查询多个表数据
    Map findById4Detail(Integer id);
    //查询当日预约数
    public Integer findOrderCountByDate(String date);
    //查询指定日期之后（新增）预约数
    public Integer findOrderCountAfterDate(String date);
    //查询当日到访数
    public Integer findVisitsCountByDate(String date);
    //查询指定日期之后（新增）预约到访数
    public Integer findVisitsCountAfterDate(String date);
    //查询热门套餐是哪几个
    public List<Map> findHotSetmeal();
}
