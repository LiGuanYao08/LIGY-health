package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.Ill;

public interface IllService {
    void addIll(Ill ill);

    PageResult pagingQuery(Integer currentPage, Integer pageSize, String queryString);

    void deleteById(Integer id);

    void edit(Ill ill);

    Ill findSportById(Integer id);
}
