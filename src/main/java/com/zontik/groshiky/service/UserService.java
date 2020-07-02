package com.zontik.groshiky.service;

import com.zontik.groshiky.dao.IRoleDao;
import com.zontik.groshiky.dao.IUserDao;
import com.zontik.groshiky.model.Role;
import com.zontik.groshiky.model.Roles;
import com.zontik.groshiky.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@Transactional
@Slf4j
public class UserService implements IUserService {

    private final PasswordEncoder PasswordEncoder;
    private final IUserDao userDao;
    private final IRoleDao roleDao;

    @Autowired
    public UserService(IUserDao userDao, IRoleDao roleDao, PasswordEncoder PasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.PasswordEncoder = PasswordEncoder;
    }

    public User createUser(User user) {
        Role role = roleDao.findRoleByName(Roles.ADMIN);
        user.setRoles(Collections.singletonList(role));
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }


    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public User findUserById(Integer id) {
        User user = userDao.findById(id).orElse(null);
        if(user == null){
            log.info("No user found  by id - {}", id);
            return null;
        }
        log.info("User found by id - {}", user);
        return user;
    }

}
