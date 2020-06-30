package com.LIGY.health.controller;

import com.LIGY.constant.MessageConstant;
import com.LIGY.constant.RedisConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Setmeal;
import com.LIGY.service.SetmealService;
import com.LIGY.utils.QiniuUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.UUID;

/**
*体检套餐管理
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference
    private SetmealService setmealService;

    @Autowired
    private QiniuUtils qiniuUtils;

    //使用JedisPool操作redis服务
    @Autowired
    private JedisPool jedisPool;

    //图片上传
    @RequestMapping("/upload")
    public Result upload(@RequestParam("imgFile") MultipartFile imgFile){
        try {
            System.out.println(imgFile);
            //获取原始文件名
            String originalFilename = imgFile.getOriginalFilename();
            //获取最后一个点的索引位置
            int lastIndexOf = originalFilename.lastIndexOf(".");
            //获取文件后缀名
            String suffix = originalFilename.substring(lastIndexOf);
            //使用UUID生成随机文件名称，防止同名文件覆盖
            String fileName = UUID.randomUUID().toString() + suffix;
            //通过对象来调用方法
            qiniuUtils.upload2Qiniu(imgFile.getBytes(),fileName);
            //图片上传成功
            Result result = new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS);
           result.setData(fileName);

            //将上传图片名称存入Redis，基于Redis的Set集合存储
          jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_RESOURCES,fileName);
              return result;
        }catch (Exception e){
            e.printStackTrace();
            //图片上传失败
            return  new Result(false,MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    //新增套餐
    @RequestMapping("/add")
    public Result add(@RequestBody Setmeal setmeal,Integer[] checkgroupIds){
        try {
            setmealService.add(setmeal,checkgroupIds);
        } catch (Exception e) {

            //新增套餐失败
            return new Result(false,MessageConstant.ADD_SETMEAL_FAIL);
        }
        //新增套餐成功
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }
    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = setmealService.pageQuery(
          queryPageBean.getCurrentPage(),
          queryPageBean.getPageSize(),
          queryPageBean.getQueryString()
        );
        return pageResult;
    }
    //全查，订单页面回显
    @RequestMapping("/findAll")
    public Result add(){
        List<Setmeal> setmealList = setmealService.findAll();
        if (setmealList !=null && setmealList.size() > 0){
            Result result = new Result(true,MessageConstant.QUERY_SETMEAL_SUCCESS);
            result.setData(setmealList);
            return result;
        }else {
            return new Result(false,MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/findById")
    public Result queryById(Integer id){
        try {
            Setmeal setmeal = setmealService.findById(id);
            return new Result(true,MessageConstant.FIND_ILLKONW_SUCCESS,setmeal);
        }catch (Exception E){
            return new Result(false,MessageConstant.FIND_ILLKONW_FAIL);
        }

    }

    @RequestMapping("/editSetmealById")
    public Result editMemberById(@RequestBody Setmeal setmeal) {
        try {
            setmealService.editSetmealById(setmeal);
            return new Result(true, MessageConstant.UPDATE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPDATE_USER_FAIL);
        }
    }

    @RequestMapping("/delete")
    public Result delete(Integer id){
        try {
            setmealService.delete(id);
        }catch (RuntimeException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new Result(false,e.getMessage());
        }catch (Exception e ){
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }


}
