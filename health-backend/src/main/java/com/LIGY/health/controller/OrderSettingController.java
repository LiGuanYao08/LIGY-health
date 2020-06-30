package com.LIGY.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.OrderSetting;
import com.LIGY.service.OrderSettingService;
import com.LIGY.utils.DateUtils;
import com.LIGY.utils.POIUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 预约设置
 */
@RestController
@RequestMapping("/orderSetting")
public class OrderSettingController {
    @Reference
    private OrderSettingService orderSettingService;


    //获取指定请求名的json；excel文件上传，解析内容并保存到数据库
    @RequestMapping("/upload")
    public Result upload(@RequestParam("excelFile")MultipartFile excelFile){
        try {
            //读取excel文件,list集合保存全部内容，数组保存每一行
            List<String[]> list = POIUtils.readExcel(excelFile);
            //把list的内容进行整理
            if (list!=null&&list.size()>0){
                List<OrderSetting> orderSettinglist = new ArrayList<>();
                //把数组的值取出来，date从yyyy/MM/dd转化为原始形态,数组转为int
                for (String[] strings:list){
                    //把时间和预约数量转化装进去
                    String _orderDate = strings[0];
                    Date orderDate = DateUtils.parseString2Date(_orderDate,"yyyy/MM/dd");
                    //这里也可以使用new Date(strings[0]),简单暴力；

                    Integer number = Integer.parseInt(strings[1]);
                    OrderSetting orderSetting = new OrderSetting(orderDate,number);
                    orderSettinglist.add(orderSetting);
                }
                //把整理好的list集合（包含orderSetting对象）传给service
                orderSettingService.add(orderSettinglist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //文件解析失败
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
        return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
    }

    //按照月份获取预定信息
    @RequestMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        //date为yy-MM
        try {
            List<Map> mapList = orderSettingService.getOrderSettingByMonth(date);
            //获取预约设置数据成功
            return new Result(true,MessageConstant.GET_ORDERSETTING_SUCCESS,mapList);
        } catch (Exception e) {
            e.printStackTrace();
            //获取预约数据失败
            return new Result(false,MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    //按照指定日期修改可预约人数
    @RequestMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting){
        try{
            orderSettingService.editNumberByDate(orderSetting);
            //修改成功
            return new Result(true,MessageConstant.ORDERSETTING_SUCCESS);
        }catch (Exception e){
            e.printStackTrace();
            //修改失败
            return new Result(false,MessageConstant.ORDERSETTING_FAIL);
        }
    }

    //获取预约单详情
    @RequestMapping("/findAll")
    public Result findAll(){
        List<Map<String,Object>> mapList = orderSettingService.findAll();
        if(mapList !=null && mapList.size()>0){
            //查询成功
            Result result = new Result(true,MessageConstant.QUERY_ORDERSETTINGLIST_SUCCESS);
            result.setData(mapList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_ORDERSETTINGLIST_FAIL);
    }

    //分页查询
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = orderSettingService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    //修改预约状态
    @RequestMapping("/updateOrderStatus")
    public Result updateOrderStatus(Integer id){
        try {
            orderSettingService.updateOrderStatus(id);
        } catch (Exception e) {
            return new Result(false,"更新状态失败");
        }
        return new Result(true,MessageConstant.ORDER_SUCCESS);
    }

    //取消预约状态
    @RequestMapping("/deleteOrderStatus")
    public  Result deleteOrderStatus(Integer id){
        try {
            orderSettingService.deleteOrderStatus(id);
        } catch (Exception e) {
            return new Result(false,"更新状态失败");
        }
        return new Result(true,"已取消预约");
    }

    //新增预约单
    @RequestMapping("/addOrder")
    public Result addOrder(@RequestBody Map<String,Object> map,Integer[] setmealIds){
        try {
            orderSettingService.addOrder(map,setmealIds);
        } catch (Exception e) {
            return new Result(false,"新增预约失败");
        }
        return new Result(true,"新增预约成功");
    }
}
