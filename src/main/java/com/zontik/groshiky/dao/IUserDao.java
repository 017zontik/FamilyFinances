package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.User;

public interface IUserDao {
    User findByLogin(String login);
    void createUser(User user);
}
