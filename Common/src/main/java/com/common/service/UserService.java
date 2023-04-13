package com.common.service;

import com.common.entity.User;

public interface UserService {
    String updateUser(User user, long userId);

    User findbyId(Long userId);
}
