package com.zq.service;

import com.zq.entity.User;

import java.util.List;

/**
 * @author ZQ
 * @Date 2020/6/14
 */
public interface IUserService {
    User getByUsername(String username);

    List<User> getList();
}
