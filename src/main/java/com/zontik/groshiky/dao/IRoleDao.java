package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.Role;

public interface IRoleDao {
    Role findRoleUser(String roleName);
}
