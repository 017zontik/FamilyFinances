package com.zontik.groshiky.repository;

import com.zontik.groshiky.model.Role;
import com.zontik.groshiky.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findRoleByName(Roles role);
}
