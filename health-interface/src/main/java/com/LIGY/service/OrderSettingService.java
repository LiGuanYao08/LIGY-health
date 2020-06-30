package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

public interface OrderSettingService {
    //导入新增预约信息
    void add(List<OrderSetting> list);
    //根据年月来查询预约信息，返回一个包含键值对的list
    List<Map> getOrderSettingByMonth(String date);//2019-03;
    //修改指定日期的预约信息
    void editNumberByDate(OrderSetting orderSetting);
    //查询所有预约信息
    List<Map<String,Object>> findAll();
    //分页查询
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    //跟新状态
    void updateOrderStatus(Integer id);
    //取消状态
    void deleteOrderStatus(Integer id);
    //新增预约
    void addOrder(Map<String,Object> map,Integer[] setmealIds);


}
