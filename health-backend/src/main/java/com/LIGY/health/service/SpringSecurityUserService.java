package com.LIGY.health.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.LIGY.pojo.Permission;
import com.LIGY.pojo.Role;
import com.LIGY.pojo.User;
import com.LIGY.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//@Component把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
//当组件不好进行定义分类时，我们使用Component
@Component
public class SpringSecurityUserService implements UserDetailsService {
    //使用dubbo来远程调用服务提供方获取数据库中的用户信息；

    @Reference
    private UserService userService;

    //1.根据用户名查询数据库获取用户信息
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null){
            //用户名不存在
            return null;
        }
        List<GrantedAuthority> list = new ArrayList<>();
        System.out.println(list);
        //动态为当前用户授权
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            //遍历角色集合，为用户授予角色
            list.add(new SimpleGrantedAuthority(role.getKeyword()));//权限关键字
            Set<Permission> permissions = role.getPermissions();
            for (Permission permission:permissions){
                //遍历权限集合，为用户授权,把simpleGrantedAuthority对象加入到集合
                list.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        //2.拼装spring-security User（username,password,list）到对象中
        org.springframework.security.core.userdetails.User securityUser = new org.springframework.security.core.userdetails.User(username,user.getPassword(),list);
        return securityUser;
    }
}
