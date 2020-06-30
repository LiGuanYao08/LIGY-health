package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.UserManageMapper;
import com.LIGY.pojo.Role;
import com.LIGY.pojo.User;
import com.LIGY.service.UserManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = UserManageService.class)
@Transactional
public class UserManageServiceImpl implements UserManageService {



    @Autowired
    private UserManageMapper userManageMapper;


    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //这里调用分页助手来设置分页
        PageHelper.startPage(currentPage,pageSize);
        //我们只需要设置查询条件，这个sql语句就需要自己写了，返回的查询对象赋值给page对象
        Page<User> page = userManageMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());

    }
    //编辑
    @Override
    public List<Role> findAllRole() {
        return null;
    }
    //添加用具
    @Override
    public int addUser(User user) {
        return 0;
    }
    //添加
    @Override
    public void addRole2User(Integer[] ids, Integer userId) {

    }
    //删除
    @Override
    public void delUser(Integer userId) {

    }


    //新增用户
    public void add(User user ) {
        userManageMapper.add(user);
    }


    //提交功能
    public List<Integer> findRoleIdsByUserIds(Integer id) {
        return userManageMapper.findRoleIdsByUserIds(id);
    }

    //编辑提交
    public void edit(Integer userId, Integer[] roleIds) {
        //删除中间表的关系
        userManageMapper.deleteAssociation(userId);
        //建立新的中间表关系
        setUserAndRole(userId,roleIds);

    }


    //建立检查组和与检查项的关联关系（重复使用）
    public void setUserAndRole(Integer userId,Integer[] userIds){
        //如果数组不为空
        if(userIds!=null&&userIds.length>0){
            for (Integer roleId: userIds){
                //遍历数组加入id和ids，以项id为条件，重复加入组的id来形成map
                Map<String,Integer> map = new HashMap<>();
                map.put("user_id",userId);
                map.put("role_id",roleId);
                userManageMapper.setUserAndRole(map);
            }
        }
    }
}
