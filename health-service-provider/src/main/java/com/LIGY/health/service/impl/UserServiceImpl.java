package com.LIGY.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.LIGY.health.mapper.PermissionMapper;
import com.LIGY.health.mapper.RoleMapper;
import com.LIGY.health.mapper.UserMapper;
import com.LIGY.pojo.Permission;
import com.LIGY.pojo.Role;
import com.LIGY.pojo.User;
import com.LIGY.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 用户服务
 */
@Service(interfaceClass = UserService.class)
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    //根据username查询用户信息
    @Override
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
    //查询用户的角色和权限点
    @Override
    public User findByUsername(String username) {
        User user = userMapper.selectByUsername(username);
        if (user ==null){
            return null;
        }
        Integer userId = user.getId();
        //根据id查询角色信息
        Set<Role> roleSet = roleMapper.findByUserId(userId);
        if (roleSet != null && roleSet.size() >0){
            for (Role role : roleSet) {
                Integer roleId = role.getId();
                //根据roleId来查询相关联的权限
                Set<Permission> permissions = permissionMapper.findByRoleId(roleId);
                role.setPermissions(permissions);//让角色去关联权限；
            }
        }
        user.setRoles(roleSet);//让用户去关联角色；
        return user;
    }

}
