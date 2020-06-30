package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.IllKonw;
import com.LIGY.pojo.Sport;

public interface SportService {

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);


    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    Sport findById(Integer id);

    /**
     * 疾病库修改
     * @param illKonw
     */
    void edit(Sport illKonw);

    /**
     * 根据逐渐id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 疾病库增加
     * @param illKonw
     */
    void add(Sport illKonw);

}
