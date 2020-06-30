package com.LIGY.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.BMap;
import com.LIGY.service.BMapService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * map地图控制
 */
@RestController
@RequestMapping("/bmap")
public class BMapController {
    @Reference
    private BMapService bmapService;

    //查询所有点坐标
    @RequestMapping("/findAll")
    public Result findAll(){
        List<BMap> bmapList = bmapService.findAll();
        if (bmapList != null && bmapList.size() >0){
            //查询成功
            Result result = new Result(true, MessageConstant.QUERY_MAP_SUCCESS);
            result.setData(bmapList);
            return result;
        }
        return  new Result(false,MessageConstant.QUERY_MAP_FAIL);
    }

    //分页显示
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = bmapService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    //新增坐标
    @RequestMapping("/addAddress")
    public Result addAddress(@RequestBody BMap bMap){
        try {
            bmapService.add(bMap);
        } catch (Exception e) {
            return  new Result(false,MessageConstant.ADD_ADDRESS_FAIL);
        }
        return new Result(true,MessageConstant.ADD_ADDRESS_SUCCESS);
    }

    //删除坐标
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            bmapService.delete(id);
        }catch (RuntimeException e){
            return new Result(false,e.getMessage());
        }catch (Exception e ){
            return new Result(false,MessageConstant.DELETE_ADDRESS_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_ADDRESS_SUCCESS);
    }
}
