package com.LIGY.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.Result;
import com.LIGY.service.BMapService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Reference
    private BMapService bMapService;
    //查询所有地址
    @RequestMapping("/findAll")
    public Result findAddress(){
        List bMapList = bMapService.findAll();
        if(bMapList !=null && bMapList.size()>0){
            //查询成功
            Result result = new Result(true, MessageConstant.QUERY_MAP_SUCCESS);
            result.setData(bMapList);
            return result;
        }
        return  new Result(false,MessageConstant.QUERY_MAP_FAIL);
    }

}
