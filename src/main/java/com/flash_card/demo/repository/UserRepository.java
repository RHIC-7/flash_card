package com.flash_card.demo.repository;

import com.flash_card.demo.entity.Users;

public interface UserRepository {
    int insertUser(Users users);

    int updateUserInfo(Users users);

    Users selectUser(String email);
}
