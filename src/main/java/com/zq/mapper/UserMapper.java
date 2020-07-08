package com.zq.mapper;

import com.zq.entity.User;

import java.util.List;

/**
 * @author ZQ
 */
public interface UserMapper {
    User getByUsername(String username);

    List<User> getList();
}