package com.LIGY.controller;

import com.LIGY.entity.Result;
import com.LIGY.jobs.ClearImgJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobsController {
    @Autowired
    private ClearImgJob clearImgJob;
    @RequestMapping("/clearImg")
    public Result clearImg(){
        clearImgJob.clearImg();
        return  new Result(true,"已执行的定时任务，请检查");
    }
    @RequestMapping("/switch/clearImg")
    public Result switchClearImg(){
        Boolean b = clearImgJob.setSwitch();
        if (b){
            return new Result(true, "清理开启");
        }else {
            return new Result(false,"清理关闭");
        }
    }
}
