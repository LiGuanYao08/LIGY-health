package com.LIGY.health.mapper;

import com.LIGY.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CheckItemMapper {
    //新建
    public void add(CheckItem checkItem);

    //分页条件查询
    //注意返回值是一个page对象，为了使用mybatis提供的分页插件
    public Page<CheckItem> selectByCondition(String queryString);

    //编辑检查项
    public void edit(CheckItem checkItem);
    public CheckItem findById(Integer id);

    //删除
    public void deleteById(Integer id);
    public long findCountByCheckItemId(Integer checkItemId);

    //查询所有
    @Select("select * from t_checkitem")
    List<CheckItem> findAll();
}
