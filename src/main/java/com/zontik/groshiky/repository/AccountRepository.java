package com.zontik.groshiky.repository;

import com.zontik.groshiky.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
      Account findByNameAndUserId(String name, Integer id);
      Account findAllById(Integer id);
}
