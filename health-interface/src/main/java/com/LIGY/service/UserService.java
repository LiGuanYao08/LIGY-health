package com.LIGY.service;

import com.LIGY.pojo.User;

public interface UserService {
    User selectByUsername(String username);
    User findByUsername(String username);
}
