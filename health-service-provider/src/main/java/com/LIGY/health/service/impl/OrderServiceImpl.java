package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.Result;
import com.LIGY.health.mapper.MemberMapper;
import com.LIGY.health.mapper.OrderMapper;
import com.LIGY.health.mapper.OrderSettingMapper;
import com.LIGY.pojo.Member;

import com.LIGY.pojo.Order;
import com.LIGY.pojo.OrderSetting;
import com.LIGY.service.OrderService;
import com.LIGY.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 体检预约服务
 */
@Service(interfaceClass = OrderService.class)
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderSettingMapper orderSettingMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;
    //体检预约
    @Override
    public Result order(Map map) throws Exception {
        //1.检查当前日期是否进行了预约设置，即当天是否可以预约
        String orderDate = (String) map.get("orderDate");
        //System.out.println(map.toString());
        Date date = DateUtils.parseString2Date(orderDate);
        //1*新建通过日期查询订单的功能
        OrderSetting orderSetting = orderSettingMapper.findByOrderDate(date);
        if(orderSetting == null){
            //所选日期没有对象，说明该日期不能预约；
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        //2.检查预约日期是否预约已满，约满则不能预约
        int number = orderSetting.getNumber();//可预约人数
        int reservations = orderSetting.getReservations();//已预约人数
        if (reservations >= number){
            //预约已满，不能预约
            return new Result(false,MessageConstant.ORDER_FULL);
        }

        //3.检查当前用户是否为会员，通过前端传回来的map中的手机号
        String telephone = (String) map.get("telephone");
        //2*新建通过手机号查询会员信息的功能
        Member member = memberMapper.findByTelephone(telephone);
        //判断是否为会员
        if (member != null){
            //是会员，获取会员编号
            Integer memberId = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId,date,null,null,setmealId);
            //3*新建通过会员id，日期，订单id获取预约单的信息；
            //4.判断是不是已经进行了预约；
            List<Order> list = orderMapper.findByCondition(order);
            if (list != null && list.size() >0){
                //已经完成了预约，不能重复预约
                return new Result(false,MessageConstant.HAS_ORDERED);
            }
        }


        //5.可以预约，设置预约人数+1
        orderSetting.setReservations(orderSetting.getReservations()+1);
        orderSettingMapper.editReservationsByOrderDate(orderSetting);
        if (member ==null){
            //当前用户不是会员，需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String)map.get("sex"));
            member.setRegTime(new Date());
            //4*新建新增会员的方法
            memberMapper.add(member);
        }
        System.out.println(map.get("address"));
        System.out.println(map.get("addressId"));//null
        //保存预约信息到预约表，未到诊：会员id，预约日期，预约类型，到诊状态，套餐id
        Order order = new Order(null,member.getId(),date,(String)map.get("orderType"),Order.ORDERSTATUS_NO,Integer.parseInt((String)map.get("setmealId")),(Integer)map.get("addressId"));
        //5*新建新增预约单的功能
        orderMapper.add(order);
        return new Result(true,MessageConstant.ORDER_SUCCESS,order.getId());
    }

    //成功页面的回显查询
    @Override
    public Map findById(Integer id) throws Exception {
        //这里的map包括体检人姓名，预约日期，套餐名称，预约类型，等参数，涉及四张表
        Map map = orderMapper.findById4Detail(id);
        if (map!=null){
            //处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate",DateUtils.parseDate2String(orderDate));
        }
        return map;
    }
}
