package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.constant.RedisConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.SetmealMapper;
import com.LIGY.pojo.Setmeal;
import com.LIGY.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    //使用JedisPool操作redis服务
    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private SetmealMapper setmealMapper;

    //新增套餐
    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealMapper.add(setmeal);
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            //绑定套餐和检查组的多对多关系
            setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        }

    //将图片保存在redis中

        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES,setmeal.getImg());

    }

    //分页查询
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Setmeal> page = setmealMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }

    //查询套餐列表
    @Override
    public List<Setmeal> findAll() {
        return setmealMapper.findAll();
    }

    //根据id查询套餐详情
    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }

    //查询预约的套餐数量
    @Override
    public List<Map<String, Object>> findSetmealCount() {
        return  setmealMapper.findSetmealCount();
    }

    @Override
    public void editSetmealById(Setmeal setmeal) {
//      Setmeal one = setmealMapper.findById(setmeal);
        setmealMapper.updateSetmeal(setmeal);
    }
    //删除
    @Override
    public void delete(Integer id) {

        setmealMapper.deleteSetmeal(id);
    }

    //绑定套餐和检查组的多对多关系
    private void setSetmealAndCheckGroup(Integer id,Integer[] checkgroupIds){
        for (Integer checkgroupId : checkgroupIds) {
            Map<String,Integer> map = new HashMap<>();
            map.put("setmeal_id",id);
            map.put("checkgroup_id",checkgroupId);
            setmealMapper.setSetmealAndCheckGroup(map);
        }
    }


}
