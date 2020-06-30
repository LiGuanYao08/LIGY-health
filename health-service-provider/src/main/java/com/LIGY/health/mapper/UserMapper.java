package com.LIGY.health.mapper;

import com.LIGY.pojo.User;

public interface UserMapper {
    User selectByUsername(String username);

}

