package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDao extends JpaRepository<Account, Integer> {
      Account findByName(String name);
}
