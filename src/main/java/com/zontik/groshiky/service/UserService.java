package com.zontik.groshiky.service;

import com.zontik.groshiky.repository.RoleRepository;
import com.zontik.groshiky.repository.UserRepository;
import com.zontik.groshiky.model.Role;
import com.zontik.groshiky.model.Roles;
import com.zontik.groshiky.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@Transactional
public class UserService implements IUserService {

    private final PasswordEncoder PasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder PasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.PasswordEncoder = PasswordEncoder;
    }

    public User createUser(User user) {
        Role role = roleRepository.findRoleByName(Roles.ADMIN);
        user.setRoles(Collections.singletonList(role));
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

}
