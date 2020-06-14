package com.zq.service;

import com.zq.entity.User;

/**
 * @author ZQ
 * @Date 2020/6/14
 */
public interface IUserService {
    User getByUsername(String username);
}
