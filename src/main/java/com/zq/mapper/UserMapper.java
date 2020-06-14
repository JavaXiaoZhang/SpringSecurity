package com.zq.mapper;

import com.zq.entity.User;

/**
 * @author ZQ
 */
public interface UserMapper {
    User getByUsername(String username);
}