package com.LIGY.health.service.impl;

import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.SportMapper;
import com.LIGY.pojo.Sport;
import com.LIGY.service.SportService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class SportServiceImpl implements SportService {


    @Autowired
    private SportMapper diseaseMapper;

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Sport> page = diseaseMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
    //查询
    @Override
    public Sport  findById( Integer id) {
        return diseaseMapper.findById(id);
    }
    //编辑
    @Override
    public void edit(Sport sport) {
        diseaseMapper.edit(sport);
    }
    //删除
    @Override
    public void delete(Integer id) {
        diseaseMapper.delete(id);
    }
    //添加
    @Override
    public void add(Sport illKonw) {
        diseaseMapper.add(illKonw);
    }
}
