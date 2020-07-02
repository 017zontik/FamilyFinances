package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IUserDao extends JpaRepository<User, Integer> {
    User findByLogin(String login);
}
