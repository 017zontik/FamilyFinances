package com.zontik.groshiky.service;

import com.zontik.groshiky.model.User;

public interface IUserService  {
    User createUser (User user);
    User findByLogin(String login);
    User findUserById(Integer id);
}
