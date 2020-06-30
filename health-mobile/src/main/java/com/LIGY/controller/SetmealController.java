package com.LIGY.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Setmeal;
import com.LIGY.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* 获取套餐信息
* */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    //获取所有的套餐信息
    @RequestMapping("/getSetmeal")
    public Result getSetmeal(){
        try {
            List<Setmeal> list = setmealService.findAll();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS,list);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }
    //根据id来查询套餐信息
    @RequestMapping("/findById")
    public Result findById(Integer id){
        try {
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS,setmeal);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }


}
