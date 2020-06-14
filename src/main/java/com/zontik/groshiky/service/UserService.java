package com.zontik.groshiky.service;

import com.zontik.groshiky.dao.IUserDao;
import com.zontik.groshiky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements IUserService{

    private final IUserDao userDao;

    @Autowired
    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void createUser(User user) {
        userDao.createUser(user);
    }

    @Override
    public User findByLogin(String login) {
       return userDao.findByLogin(login);
    }

}
