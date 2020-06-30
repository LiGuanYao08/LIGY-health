package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;

import java.util.Map;

public interface OrderListService {
    PageResult findByPage(QueryPageBean queryPageBean);

    void deleteById(Integer id);

    Result addOrder(Map map, Integer[] setmealIds) throws Exception;
}
