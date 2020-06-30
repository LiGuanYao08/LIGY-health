package com.LIGY.health.mapper;

import com.LIGY.pojo.Permission;

import java.util.Set;

public interface PermissionMapper {
    Set<Permission> findByRoleId(Integer roleId);

}
