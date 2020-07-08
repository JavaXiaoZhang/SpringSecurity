package com.zq.service.impl;

import com.zq.entity.User;
import com.zq.mapper.UserMapper;
import com.zq.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ZQ
 * @Date 2020/6/14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getByUsername(String username) {
        return userMapper.getByUsername(username);
    }

    @Override
    public List<User> getList() {
        return userMapper.getList();
    }
}
