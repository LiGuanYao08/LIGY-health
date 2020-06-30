package com.LIGY.health.service.impl;

import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.DiseaseMapper;
import com.LIGY.pojo.IllKonw;
import com.LIGY.service.DiseaseService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
@Service
public class DiseaseServiceImpl implements DiseaseService {


    @Autowired
    private DiseaseMapper diseaseMapper;

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<IllKonw> page = diseaseMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
    //查询疾病库
    @Override
    public IllKonw  findById( Integer id) {
        return diseaseMapper.findById(id);
    }
    //编辑疾病库
    @Override
    public void edit(IllKonw illKonw) {
        diseaseMapper.edit(illKonw);
    }
    //删除疾病库
    @Override
    public void delete(Integer id) {
        diseaseMapper.delete(id);
    }
    //添加疾病库
    @Override
    public void add(IllKonw illKonw) {
        diseaseMapper.add(illKonw);
    }
}
