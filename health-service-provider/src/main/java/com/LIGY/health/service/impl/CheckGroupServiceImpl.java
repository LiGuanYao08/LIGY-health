package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.CheckGroupMapper;
import com.LIGY.pojo.CheckGroup;
import com.LIGY.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    //添加检查组合，同时需要设置检查组合和检查项的关联关系
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        //插入检查组+接收返回的主键
        checkGroupMapper.add(checkGroup);
        //将checkGroup中已经封装好的id属性和检查项数组调用下面方法
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
    }

    //建立检查组和与检查项的关联关系（重复使用）
    public void setCheckGroupAndCheckItem(Integer checkGroupId,Integer[] checkitemIds){
        //如果数组不为空
        if(checkitemIds!=null&&checkitemIds.length>0){
            for (Integer checkitemId: checkitemIds){
                //遍历数组加入id和ids，以项id为条件，重复加入组的id来形成map
                Map<String,Integer>map = new HashMap<>();
                map.put("checkgroup_id",checkGroupId);
                map.put("checkitem_id",checkitemId);
                checkGroupMapper.setCheckGroupAndCheckItem(map);
            }
        }

    }

    //实现检查项的分页显示
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckGroup> page = checkGroupMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }


    //查询检查组的基本信息
    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupMapper.findById(id);
    }
    //查询检查组的外键，用于勾选回显
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupMapper.findCheckItemIdsByCheckGroupId(id);
    }

    //编辑完数据的提交
    @Override
    public void edit(CheckGroup checkGroup, Integer[] checkitemIds) {
        //首先要删除中间表关系
        checkGroupMapper.findCountByCheckItemId(checkGroup.getId());
        //向中间表新建关系
        setCheckGroupAndCheckItem(checkGroup.getId(),checkitemIds);
        //更新检查组的基本信息
        checkGroupMapper.edit(checkGroup);
    }


    //查询所有
    @Override
    public List<CheckGroup> findAll() {
        return checkGroupMapper.findAll();
    }




    //  删除
    @Override
    public void delete(Integer id) {
        checkGroupMapper.delete(id);
    }



}
