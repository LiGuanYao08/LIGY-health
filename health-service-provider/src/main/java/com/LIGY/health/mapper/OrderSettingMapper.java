package com.LIGY.health.mapper;

import com.github.pagehelper.Page;
import com.LIGY.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderSettingMapper {
    //新增预约订单
    void add(OrderSetting orderSetting);

    //更新预约订单
    void editNumberByOrderDate(OrderSetting orderSetting);

    //查询指定日期的订单数量
    long findCountByOrderDate(Date orderDate);

    //查询每一单月的预约信息
    List<OrderSetting> getOrderSettingByMonth(Map date);

    //编辑指定日期的预约信息
    void editNumberByDate(OrderSetting orderSetting);

    //更新指定日期的已预约的人数
    void editReservationsByOrderDate(OrderSetting orderSetting);

    //查询指定日期预约所有信息
    OrderSetting findByOrderDate(Date orderDate);

    //查询全部预约信息
    List<Map<String,Object>> findAll();

    //分页查询
    Page<Map<String,Object>> selectByCondition(Map map);

    //更新预约状态
    void updateOrderStatus(Integer id);

    //取消预约状态
    void deleteOrderStatus(Integer id);

    //新增预约表
    void addOrder(Map<String,Object> map);
    //先加会员表
    void addMember(Map<String,Object> map);
}