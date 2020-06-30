package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.BMap;

import java.util.List;

public interface BMapService {
    List<BMap> findAll();
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
    List findAddress();
    void add(BMap bMap);
    void delete(Integer id);
}
