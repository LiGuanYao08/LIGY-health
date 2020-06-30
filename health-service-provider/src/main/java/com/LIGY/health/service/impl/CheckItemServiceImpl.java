package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.CheckItemMapper;
import com.LIGY.pojo.CheckItem;
import com.LIGY.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查服务项
 */
@Service(interfaceClass = CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;

    //新增
    public void add(CheckItem checkItem) {
     checkItemMapper.add(checkItem);
    }

    //分页查询
    public PageResult pageQuery(Integer currentPage,Integer pageSize,String queryString){
        //这里调用分页助手来设置分页
        PageHelper.startPage(currentPage,pageSize);
        //我们只需要设置查询条件，这个sql语句就需要自己写了，返回的查询对象赋值给page对象
        Page<CheckItem> page = checkItemMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //编辑
    @Override
    public void edit(CheckItem checkItem) {
        checkItemMapper.edit(checkItem);
    }
    //编辑查询信息
    @Override
    public CheckItem findById(Integer id) {
        return checkItemMapper.findById(id);
    }

    //删除
    @Override
    public void delete(Integer id) throws RuntimeException{

        long count = checkItemMapper.findCountByCheckItemId(id);
        if (count > 0){

            //当前检查项被引用，不能删除
            throw new RuntimeException("当前检查项被应用，不能删除");
        }
        checkItemMapper.deleteById(id);
    }

    //查询所有
    @Override
    public List<CheckItem> findAll() {
        return checkItemMapper.findAll();
    }
}
