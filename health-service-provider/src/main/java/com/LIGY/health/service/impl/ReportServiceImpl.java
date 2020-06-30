package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.LIGY.health.mapper.MemberMapper;
import com.LIGY.health.mapper.OrderMapper;
import com.LIGY.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = ReportService.class)
@Transactional
public class ReportServiceImpl implements ReportService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private OrderMapper orderMapper;

    //运营数据统计
    @Override
    public Map<String, Object> getBusinessReportData() {
        //获得当前日期
        //String taday = DateUtils.parseDate2String(DateUtils.getToday());
        LocalDate _today = LocalDate.now();//2019-11-28,但格式是LocalDate的
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today = _today.format(formatter);//2019-11-28，转化格式为string

        //获取当前日期所在周，周一的日期
        //String thisWeekMonday = DateUtils.parseDate2String(DateUtils.getThisWeekMonday());
        LocalDate _thisWeekMonday = _today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        String thisWeekMonday = _thisWeekMonday.format(formatter);
        //获得本月第一天的日期
        //String firstDay4ThisMonth = DateUtils.parseDate2String(DateUtils.getFirstDay4ThisMonth());
        LocalDate _theMonthfirstDay = _today.with(TemporalAdjusters.firstDayOfMonth());
        String theMonthfirstDay = _theMonthfirstDay.format(formatter);


        //今日新增会员数
        Integer todayNewMember = memberMapper.findMemberCountByDate(today);
        //总会员数
        Integer totalMember = memberMapper.findMemberTotalCount();
        //本周新增会员数
        Integer thisWeekNewMember = memberMapper.findMemberCountAfterDate(thisWeekMonday);
        //本月新增会员数
        Integer thisMonthNewMember = memberMapper.findMemberCountAfterDate(theMonthfirstDay);



        //今日预约数
        Integer todayOrderNumber = orderMapper.findOrderCountByDate(today);
        //本周预约数
        Integer thisWeekOrderNumber = orderMapper.findOrderCountAfterDate(thisWeekMonday);
        //本月预约数
        Integer thisMonthOrderNumber = orderMapper.findOrderCountAfterDate(theMonthfirstDay);


        //今日到诊数
        Integer todayVisitsNumber = orderMapper.findVisitsCountByDate(today);
        //本周到诊数
        Integer thisWeekVisitsNumber = orderMapper.findVisitsCountAfterDate(thisWeekMonday);
        //本月到诊数
        Integer thisMonthVisitsNumber = orderMapper.findVisitsCountAfterDate(theMonthfirstDay);


        //热门套餐
        List<Map> hotSetmeal = orderMapper.findHotSetmeal();

        Map<String,Object> result = new HashMap<>();
        result.put("reportDate",today);
        result.put("todayNewMember",todayNewMember);
        result.put("totalMember",totalMember);
        result.put("thisWeekNewMember",thisWeekNewMember);
        result.put("thisMonthNewMember",thisMonthNewMember);
        result.put("todayOrderNumber",todayOrderNumber);
        result.put("thisWeekOrderNumber",thisWeekOrderNumber);
        result.put("thisMonthOrderNumber",thisMonthOrderNumber);
        result.put("todayVisitsNumber",todayVisitsNumber);
        result.put("thisWeekVisitsNumber",thisWeekVisitsNumber);
        result.put("thisMonthVisitsNumber",thisMonthVisitsNumber);
        result.put("hotSetmeal",hotSetmeal);

        return result;
    }
}
