package com.LIGY.health.mapper;

import com.alibaba.fastjson.JSON;
import com.LIGY.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-*.xml")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectByUsername() {
        User admin = userMapper.selectByUsername("admin");
        System.out.println(JSON.toJSONString(admin));
    }
}