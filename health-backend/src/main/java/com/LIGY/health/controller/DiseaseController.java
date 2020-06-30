package com.LIGY.health.controller;

import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.IllKonw;
import com.LIGY.service.DiseaseService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/disease")
public class DiseaseController {

    @Reference
    private DiseaseService diseaseService;




    //新增功能
    @RequestMapping("/add")
    public Result add (@RequestBody IllKonw illKonw){
        try {
            diseaseService.add(illKonw);
            //新增成功
            return new Result(true,MessageConstant.ADD_ILLKONW_SUCCESS);
        }catch (Exception e ){
            //新增失败
            return new Result(false, MessageConstant.ADD_ILLKONW_FAIL);
        }

    }

    //查询疾病库数据
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = diseaseService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }



    //编辑疾病库数据
    @RequestMapping("/edit")
    public Result edit(@RequestBody IllKonw illKonw){
        try {
            diseaseService.edit(illKonw);
            return  new Result(true,MessageConstant.EDIT_ILLKONW_SUCCESS);
        }catch (Exception e){
            return new Result(false,MessageConstant.EDIT_ILLKONW_FAIL);
        }

    }
    //删除疾病库数据
    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            diseaseService.delete(id);
            return new Result(true,MessageConstant.DELETE_ILLKONW_SUCCESS);
        }catch (Exception E){
            return new Result(false,MessageConstant.DELETE_ILLKONW_FAIL);
        }

    }
    //查询疾病库数据
    @RequestMapping("/findById")
    public Result queryById(Integer id){
        try {
            IllKonw illKonw = diseaseService.findById(id);
            return new Result(true,MessageConstant.FIND_ILLKONW_SUCCESS,illKonw);
        }catch (Exception E){
            return new Result(false,MessageConstant.FIND_ILLKONW_FAIL);
        }

    }
}
