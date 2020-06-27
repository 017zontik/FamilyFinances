package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.Role;
import com.zontik.groshiky.model.Roles;

public interface IRoleDao {
    Role findRoleUser(Roles roleName);
}
