package com.LIGY.health.mapper;

import com.LIGY.pojo.IllKonw;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;

public interface DiseaseMapper {

    //分页查询
    Page<IllKonw> selectByCondition(String queryString);

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    IllKonw findById( Integer id);

    /**
     * 疾病库修改
     * @param illKonw
     */
    void edit(IllKonw illKonw);

    /**
     * 根据逐渐id删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 疾病库增加
     * @param illKonw
     */
    void add(IllKonw illKonw);

}
