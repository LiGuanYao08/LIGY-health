package com.LIGY.health.service.impl;

import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.PermissionManageMapper;
import com.LIGY.pojo.Permission;
import com.LIGY.pojo.Role;
import com.LIGY.service.PermissionManageService;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = PermissionManageService.class)
@Transactional
public class PermissionManageServiceImpl implements PermissionManageService {

    @Autowired
        private PermissionManageMapper permissionManageMapper;

    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //这里调用分页助手来设置分页
        PageHelper.startPage(currentPage,pageSize);
        //我们只需要设置查询条件，这个sql语句就需要自己写了，返回的查询对象赋值给page对象
        Page<Role> page = permissionManageMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());

    }

    //查询所有权限
    public List<Permission> findAll() {
        return permissionManageMapper.findAll();
    }
    //提交表单
    public void edit(Integer roleId, Integer[] permissionIds) {
        //先删除中间表关系
        permissionManageMapper.deleteAssociation(roleId);
        //建立新的权限关系
        setPermission(roleId,permissionIds);
    }

    //新增用户
    public void add(Permission permission ) {
        permissionManageMapper.add(permission);
    }
    //删除
    @Override
    public void delete(Integer id) {
        permissionManageMapper.delete(id);
    }


    //查询
    @Override
    public Permission findById(Integer id)
    {
        return permissionManageMapper.findById(id);
    }

    //建立检查组和与检查项的关联关系（重复使用）
    public void setPermission(Integer roleId,Integer[] roleIds){
        //如果数组不为空
        if(roleIds!=null && roleIds.length>0){
            for (Integer permissionId: roleIds){
                //遍历数组加入id和ids，以项id为条件，重复加入组的id来形成map
                Map<String,Integer> map = new HashMap<>();
                map.put("role_id",roleId);
                map.put("permission_id",permissionId);
               permissionManageMapper.setPermission(map);
            }
        }
    }

    //提交功能
    public List<Integer> findPermissionIdsByRoleIds(Integer id) {
        return permissionManageMapper.findPermissionIdsByRoleIds(id);
    }

}
