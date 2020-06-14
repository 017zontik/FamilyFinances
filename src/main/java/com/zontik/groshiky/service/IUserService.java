package com.zontik.groshiky.service;

import com.zontik.groshiky.model.User;

public interface IUserService {
    void createUser (User user);
    User findByLogin(String login);
}
