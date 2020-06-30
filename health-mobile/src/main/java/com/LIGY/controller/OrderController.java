package com.LIGY.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.aliyuncs.exceptions.ClientException;
import com.LIGY.constant.MessageConstant;
import com.LIGY.constant.RedisMessageConstant;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Order;
import com.LIGY.service.OrderService;
import com.LIGY.utils.SMSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

import java.util.Map;

/*
* 体检预约
* */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Reference
    private OrderService orderService;
    @Autowired
    private JedisPool jedisPool;
    //体检预约
    @RequestMapping("/submit")
    public Result submitOrder(@RequestBody Map map){
        //从前台获取的key手机号
        String telephone =(String)map.get("telephone");
        //从redis中获取缓存的验证码,key为手机号+常量
        String codeInRedis = jedisPool.getResource().get(telephone + RedisMessageConstant.SENDTYPE_ORDER);
        String validdateCode = (String) map.get("validateCode");
        //1.校验手机验证码；redis中的和用户输入的
        if (codeInRedis == null || !codeInRedis.equals(validdateCode)){
            //校验失败
            return new Result(false, MessageConstant.VALIDATECODE_ERROR);
        }
        Result result = null;
        //2.调用体检预约服务
        try{
            //把预约类型加入map中
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            //通过Dubbo远程调用服务order实现在线预约业务处理
            result = orderService.order(map);
        }catch (Exception e){
            e.printStackTrace();
            //预约失败
            return result;
        }
        if (result.isFlag()){
            //预约成功,发送短信通知
            String orderDate = (String) map.get("orderDate");
            try{
                //调用工具类发短信（预约成功通知）
                SMSUtils.sendShortMessage(SMSUtils.ORDER_NOTICE,telephone,orderDate);
            }catch (ClientException e){
                e.printStackTrace();
            }
        }
        return result;
    }

    //预约成功界面的数据回显查询
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Map map = orderService.findById(id);
            //查询预约信息成功
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,map);
        }catch (Exception e ){
            e.printStackTrace();
            //查询预约信息失败
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }
    }
}
