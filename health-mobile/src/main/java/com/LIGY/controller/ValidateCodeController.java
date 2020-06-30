package com.LIGY.controller;

import com.aliyuncs.exceptions.ClientException;
import com.LIGY.constant.MessageConstant;
import com.LIGY.constant.RedisMessageConstant;
import com.LIGY.entity.Result;
import com.LIGY.utils.SMSUtils;
import com.LIGY.utils.ValidateCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.JedisPool;

/*
发送短信验证码,将验证码保存到redis中;
 */
@RestController
@RequestMapping("/validateCode")
public class ValidateCodeController {
    @Autowired
    private JedisPool jedisPool;

    //体检预约时发送手机验证码
    @RequestMapping("/send4Order")
    public Result send4Order(String telephone){
        //通过工具类来生成4位数的随机码
        Integer code = ValidateCodeUtils.generateValidateCode(4);
        try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        }catch (ClientException e){
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //发送验证码成功
        System.out.println("发送的手机验证码:"+code);
        //将生成的验证码缓存到redis（5分钟）
        //setex(key,seconds)方法来设置有效时间；set()方法不可
        //这里再手机号＋redis常量信息来表示状态并避免重复
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_ORDER,5*60,code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }


    //登录验证时发送手机验证码
    @RequestMapping("/send4Login")
    public Result send4Login(String telephone){
        //通过工具类来生成4位数的随机码
        Integer code = ValidateCodeUtils.generateValidateCode(6);
        try {
            //发送短信
            SMSUtils.sendShortMessage(SMSUtils.VALIDATE_CODE,telephone,code.toString());
        }catch (ClientException e){
            e.printStackTrace();
            //验证码发送失败
            return new Result(false, MessageConstant.SEND_VALIDATECODE_FAIL);
        }
        //发送验证码成功
        System.out.println("发送的手机验证码:"+code);
        //将生成的验证码缓存到redis（5分钟）
        //setex(key,seconds)方法来设置有效时间；set()方法不可
        //这里再手机号＋redis常量信息来表示状态并避免重复
        jedisPool.getResource().setex(telephone + RedisMessageConstant.SENDTYPE_LOGIN,5*60,code.toString());
        //验证码发送成功
        return new Result(true,MessageConstant.SEND_VALIDATECODE_SUCCESS);
    }
}
