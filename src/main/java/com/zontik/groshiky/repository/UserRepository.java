package com.zontik.groshiky.repository;

import com.zontik.groshiky.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByLogin(String login);

}
