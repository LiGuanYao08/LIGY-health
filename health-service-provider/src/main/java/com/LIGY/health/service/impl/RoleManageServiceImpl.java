package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.RoleManageMapper;
import com.LIGY.pojo.Role;
import com.LIGY.service.RoleManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = RoleManageService.class)
@Transactional
public class RoleManageServiceImpl implements RoleManageService {

    @Autowired
    private RoleManageMapper roleManageMapper;

    //分页查询
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        //这里调用分页助手来设置分页
        PageHelper.startPage(currentPage,pageSize);
        //我们只需要设置查询条件，这个sql语句就需要自己写了，返回的查询对象赋值给page对象
        Page<Role> page = roleManageMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());

    }

    //数据的回显
    public List<Integer> findPermissionIdsByRoleIds(Integer id) {
        return roleManageMapper.findPermissionIdsByRoleIds(id);
    }


    //新增用户
    public void add(Role role ) {
        roleManageMapper.add(role);
    }


    //查询所有角色
    public List<Role> findAll() {
      return  roleManageMapper.findAll();
    }


    //提交表单
    public void edit(Integer roleId, Integer[] permissionIds) {
        //先删除中间表关系
        roleManageMapper.deleteAssociation(roleId);
        //建立新的权限关系
        setRoleAndPermission(roleId,permissionIds);
    }


    //建立检查组和与检查项的关联关系（重复使用）
    public void setRoleAndPermission(Integer roleId,Integer[] roleIds){
        //如果数组不为空
        if(roleIds!=null && roleIds.length>0){
            for (Integer permissionId: roleIds){
                //遍历数组加入id和ids，以项id为条件，重复加入组的id来形成map
                Map<String,Integer> map = new HashMap<>();
                map.put("role_id",roleId);
                map.put("permission_id",permissionId);
                roleManageMapper.setRoleAndPermission(map);
            }
        }
    }
}
