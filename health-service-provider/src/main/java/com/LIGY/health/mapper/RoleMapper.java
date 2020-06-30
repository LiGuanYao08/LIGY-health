package com.LIGY.health.mapper;

import com.LIGY.pojo.Role;

import java.util.Set;

public interface RoleMapper {
    Set<Role> findByUserId(Integer userId);
}
