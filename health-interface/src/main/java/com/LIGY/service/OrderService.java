package com.LIGY.service;

import com.LIGY.entity.Result;

import java.util.Map;

public interface OrderService {
    //体检预约
    Result order(Map map) throws Exception;
    //预约成功的回显查询
    Map findById(Integer id) throws Exception;
}
