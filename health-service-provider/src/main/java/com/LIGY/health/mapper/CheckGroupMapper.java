package com.LIGY.health.mapper;

import com.LIGY.pojo.CheckGroup;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
import java.util.Map;

/**
 * 持久层接口
 */
public interface CheckGroupMapper {
    //增加检查组
    @Insert("insert into t_checkgroup(code,name,sex,helpCode,remark,attention) " +
          "values (#{code},#{name},#{sex},#{helpCode},#{remark},#{attention})")
    //返回自增的主键
    @SelectKey(keyProperty ="id",keyColumn = "id",before = false,statement = "select last_insert_id()",resultType = Integer.class)
    void add(CheckGroup checkGroup);

    //建立中间表关系（复用）
    @Insert("insert into t_checkgroup_checkitem(checkgroup_id,checkitem_id) " +
            "values (#{checkgroup_id},#{checkitem_id})")
    void setCheckGroupAndCheckItem(Map map);

    //分页查询

    Page<CheckGroup> selectByCondition(String queryString);

    //查询检查组基本信息，用于回显
    CheckGroup findById(Integer id);

    //查询检查组基本信息，回显勾选
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    //删除掉原有的中间表关系

    public void deleteById(Integer id);
    public long findCountByCheckItemId(Integer checkGroupId);

    //提交检查组的基本信息
    void edit(CheckGroup checkGroup);

    //查询所有的检查组
    List<CheckGroup> findAll();
    /**
     * 根据检查项id删除检查项
     * @param id 检查项id
     */
//    @Delete("delete from t_checkgroup where id = #{id}")
    void delete(Integer id);

    /**
     * 根据groupId删除检查组检查项相关的中间表数据
     * @param id 根据检查项id删除检查项所关联得所有检查组关联关系
     */
    @Delete("delete from t_checkgroup_checkitem where checkgroup_id = #{id}")
    void deleteGIByGId(Integer id);

}
