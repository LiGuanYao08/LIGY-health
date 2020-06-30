package com.LIGY.health.mapper;

import com.LIGY.pojo.Sport;
import com.github.pagehelper.Page;

public interface SportMapper {

    //分页查询
    Page<Sport> selectByCondition(String queryString);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    Sport findById(Integer id);

    /**
     * 疾病库修改
     * @param sport
     */
    void edit(Sport sport);

    /**
     * 根据逐渐id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 疾病库增加
     * @param sport
     */
    void add(Sport sport);

}
