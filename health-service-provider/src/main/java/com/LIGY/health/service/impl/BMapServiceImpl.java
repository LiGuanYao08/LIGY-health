package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.BMapMapper;
import com.LIGY.pojo.CheckItem;
import com.LIGY.pojo.BMap;
import com.LIGY.service.BMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = BMapService.class)
@Transactional
public class BMapServiceImpl implements BMapService {
    @Autowired
    private BMapMapper bmapMapper;

    //查询所有
    @Override
    public List<BMap> findAll() {
        return bmapMapper.findAll();
    }

    //分页显示
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //这里调用分页助手来设置分页
        PageHelper.startPage(currentPage,pageSize);
        //我们只需要设置查询条件，这个sql语句就需要自己写了，返回的查询对象赋值给page对象
        Page<CheckItem> page = bmapMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //只查询地址
    @Override
    public List findAddress() {
        return bmapMapper.findAddress();
    }

    //添加新地址
    @Override
    public void add(BMap bMap) {
        bmapMapper.add(bMap);
    }

    //删除新地址
    @Override
    public void delete(Integer id) {
        long count = bmapMapper.findCountByCheck(id);
        if (count > 0){
            //当前检查项被引用，不能删除
            throw new RuntimeException("当前地址被引用，不可删除");
        }
        bmapMapper.deleteById(id);
    }
}
