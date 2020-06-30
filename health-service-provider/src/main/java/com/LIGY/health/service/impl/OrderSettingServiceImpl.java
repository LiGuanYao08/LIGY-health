package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.OrderSettingMapper;
import com.LIGY.pojo.OrderSetting;
import com.LIGY.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service(interfaceClass = OrderSettingService.class)
@Transactional
public class    OrderSettingServiceImpl implements OrderSettingService{

    @Autowired
    private OrderSettingMapper orderSettingMapper;

    //批量添加预约信息
    @Override
    public void add(List<OrderSetting> list) {
        if(list!=null&&list.size()>0){
            for (OrderSetting orderSetting:list){
                //校验此日期是否存在了预约设置；
                long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
                if (count>0){
                    //已经存在了预约，执行更新操作；
                    orderSettingMapper.editNumberByOrderDate(orderSetting);
                }else {
                    //不存在预约，执行添加操作；
                    orderSettingMapper.add(orderSetting);
                }
            }
        }
    }

    //根据年月来查询预约信息
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        String begin = date +"-1";//2019-9-1
        String end = date +"-31";//2019-9-31
        Map<String,String> map = new HashMap<>();
        map.put("begin",begin);
        map.put("end",end);
        //调用dao，查询预约信息
        List<OrderSetting> list = orderSettingMapper.getOrderSettingByMonth(map);
        //map这个变量使用完毕；
        List<Map> result  = new ArrayList<>();
        if (list != null && list.size() > 0){
            for (OrderSetting orderSetting : list) {
                Map<String,Object> maplist = new HashMap<>();
                maplist.put("date",orderSetting.getOrderDate().getDate());//获取得是一个int类型的日号；
                maplist.put("number",orderSetting.getNumber());//可预约人数
                maplist.put("reservations",orderSetting.getReservations());//已预约人数
                result.add(maplist);
            }
        }
        return result;
    }

    //根据日期修改可预约人数
    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingMapper.findCountByOrderDate(orderSetting.getOrderDate());
        if (count>0){
            //当前已有预约，进行修改
            orderSettingMapper.editNumberByDate(orderSetting);
        }else {
            //当前没有预约，进行新增
            orderSettingMapper.add(orderSetting);
        }
    }

    //查询全部预约信息
    @Override
    public List<Map<String,Object>> findAll() {
        return orderSettingMapper.findAll();
    }

    //分页查询
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString){
        //这里调用分页助手来设置分页
        PageHelper.startPage(currentPage,pageSize);
        Map<String, Object> map = new HashMap<>();

        if (queryString !=null && queryString.length()>0){
            //说明有条件
            for (int i = 0; i < queryString.length(); i++) {
                if (!Character.isDigit(queryString.charAt(i))) {
                    //说明不是数字
                    map.put("name",queryString);
                }else {
                    //说明是数字
                    map.put("phoneNumber",queryString);
                }
            }
        }
        //我们只需要设置查询条件，这个sql语句就需要自己写了，返回的查询对象赋值给page对象
        Page<Map<String,Object>> page = orderSettingMapper.selectByCondition(map);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //更新预约状态
    @Override
    public void updateOrderStatus(Integer id) {
        orderSettingMapper.updateOrderStatus(id);
    }

    //取消预约状态
    @Override
    public void deleteOrderStatus(Integer id) {
        orderSettingMapper.deleteOrderStatus(id);
    }
    //新增订单
    @Override
    public void addOrder(Map<String,Object> map,Integer[] setmealIds) {
        //先加member表，添加会员和注册时间
        orderSettingMapper.addMember(map);
        //然后map里有了主键,添加主键
        Integer id = (Integer)map.get("id");
        map.put("memberId",id);
        map.put("regTime",new Date());
        map.put("orderType","电脑预约");
        map.put("orderStatus","未就诊");
        //封装map中setmealId数组对象
        if(setmealIds!=null&&setmealIds.length>0){
            for (Integer setmealId: setmealIds){
                //遍历数组加入id和ids，以项id为条件，重复加入组的id来形成map
                map.put("setmealId",setmealId);
                //多次加入订单表
                orderSettingMapper.addOrder(map);
            }
        }
    }

}
