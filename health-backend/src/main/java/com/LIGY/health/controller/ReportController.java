package com.LIGY.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.Result;
import com.LIGY.service.MemberService;
import com.LIGY.service.ReportService;
import com.LIGY.service.SetmealService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 报表操作
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    //引入会员服务
    @Reference
    private MemberService memberService;

    @Reference
    private SetmealService setmealService;

    @Reference
    private ReportService reportService;

    @RequestMapping("/getMemberReport")
    public Result getMemberReport(){
       try {
            //获得一个Calender对象，这个对象携带目前的系统时间
            Calendar calendar = Calendar.getInstance();
            //修改calendar的日期到12个月前，
            calendar.add(Calendar.MONTH,-12);
            List<String> list =new ArrayList<>();
            Map<String,Object> map = new HashMap<>();
            for (int i = 0; i <12; i++) {
                //月份依次加1
                calendar.add(Calendar.MONTH,1);
                //头通过getTime()来获取每一个月的原始日期格式并转化；
                list.add(new SimpleDateFormat("yyyy-MM").format(calendar.getTime()));
                map.put("months",list);
                //在sql中常见的日期格式都可以被自动解析
                List<Integer> memberCount = memberService.findMemberCountByMonth(list);
                map.put("memberCount",memberCount);

            }
            return new Result(true,MessageConstant.GET_MEMBER_NUMBER_REPORT_SUCCESS,map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_MEMBER_NUMBER_REPORT_FAIL);
        }

    }

    @RequestMapping("/getSetmealReport")
    public Result getSetmealReport(){
        List<Map<String,Object>> list = setmealService.findSetmealCount();
        HashMap<String, Object> map = new HashMap<>();
        map.put("setmealCount",list);
        List<String> setmealNames = new ArrayList<>();
        for (Map<String, Object> m : list) {
            //get("name")获取的是map中name的值"套餐1"；
            String name = (String)m.get("name");
            //将value的值依次加入集合
            setmealNames.add(name);
        }
        map.put("setmealNames",setmealNames);

        return new Result(true,MessageConstant.GET_SETMEAL_COUNT_REPORT_SUCCESS,map);
    }

    @RequestMapping("/getBusinessReportData")
    public Result getBusinessReportData(){
        try {
            Map<String, Object> reportData = reportService.getBusinessReportData();
            return new Result(true,MessageConstant.GET_BUSINESS_REPORT_SUCCESS,reportData);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }

    @RequestMapping("/exportBusinessReport")
    public Result exportBusinessReport(HttpServletRequest request, HttpServletResponse response){
        try {
            //远程调用报表服务再获取报表数据
            Map<String,Object> result = reportService.getBusinessReportData();
            //然后将结果数据，写入到EXCEl文件中，先把各个数据进行类型转换
            String reportDate = (String) result.get("reportDate");
            Integer todayNewMember = (Integer) result.get("todayNewMember");
            Integer totalMember = (Integer) result.get("totalMember");
            Integer thisWeekNewMember = (Integer) result.get("thisWeekNewMember");
            Integer thisMonthNewMember = (Integer) result.get("thisMonthNewMember");
            Integer todayOrderNumber = (Integer) result.get("todayOrderNumber");
            Integer thisWeekOrderNumber = (Integer) result.get("thisWeekOrderNumber");
            Integer thisMonthOrderNumber = (Integer) result.get("thisMonthOrderNumber");
            Integer todayVisitsNumber = (Integer) result.get("todayVisitsNumber");
            Integer thisWeekVisitsNumber = (Integer) result.get("thisWeekVisitsNumber");
            Integer thisMonthVisitsNumber = (Integer) result.get("thisMonthVisitsNumber");
            List<Map> hotSetmeal = (List<Map>) result.get("hotSetmeal");
            //获得Excel模板文件绝对路径
            String templatePath = request.getSession().getServletContext().getRealPath("template") + File.separator + "report_template.xlsx";
            //读取模板文件创建Excel表格对象
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(templatePath)));
            //获取模板文件的第一张表
            XSSFSheet sheet = workbook.getSheetAt(0);
            //获取第一张表的第三行
            XSSFRow row = sheet.getRow(2);
            //在第六个单元格中加入日期数据
            row.getCell(5).setCellValue(reportDate);

            row = sheet.getRow(4);
            row.getCell(5).setCellValue(todayNewMember);//新增会员数（本日）
            row.getCell(7).setCellValue(totalMember);//总会员数

            row = sheet.getRow(5);
            row.getCell(5).setCellValue(thisWeekNewMember);//本周新增会员数
            row.getCell(7).setCellValue(thisMonthNewMember);//本月新增会员数

            row = sheet.getRow(7);
            row.getCell(5).setCellValue(todayOrderNumber);//今日预约数
            row.getCell(7).setCellValue(todayVisitsNumber);//今日到诊数

            row = sheet.getRow(8);
            row.getCell(5).setCellValue(thisWeekOrderNumber);//本周预约数
            row.getCell(7).setCellValue(thisWeekVisitsNumber);//本周到诊数

            row = sheet.getRow(9);
            row.getCell(5).setCellValue(thisMonthOrderNumber);//本月预约数
            row.getCell(7).setCellValue(thisMonthVisitsNumber);//本月到诊数

            int rowNum = 12;
            for (Map map : hotSetmeal) {
                //热门套餐
                String name = (String) map.get("name");
                Long setmeal_count = (Long) map.get("setmeal_count");
                BigDecimal proportion = (BigDecimal) map.get("proportion");
                row = sheet.getRow(rowNum++);
                row.getCell(4).setCellValue(name);//套餐名称
                row.getCell(5).setCellValue(setmeal_count);//预约数量
                row.getCell(6).setCellValue(proportion.doubleValue());//占比
            }
            //通过输出流进行文件下载
            OutputStream out = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");//代表的是Excel文件类型(固定)
            response.setHeader("content-Disposition", "attachment;filename=report.xlsx");//指定以附件形式进行下载
            workbook.write(out);

            out.flush();
            out.close();
            workbook.close();
            return null;
        } catch (Exception e) {
            return new Result(false,MessageConstant.GET_BUSINESS_REPORT_FAIL);
        }
    }
}
