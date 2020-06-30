package com.LIGY.health.mapper;

import com.github.pagehelper.Page;
import com.LIGY.pojo.CheckItem;
import com.LIGY.pojo.BMap;

import java.util.List;

public interface BMapMapper {
    Page<CheckItem> selectByCondition(String queryString);
    List<BMap> findAll();
    List findAddress();
    void add(BMap bMap);
    void deleteById(Integer id);
    long findCountByCheck(Integer id);
}
