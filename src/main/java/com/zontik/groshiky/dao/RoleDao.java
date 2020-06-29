package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.Role;
import com.zontik.groshiky.model.Roles;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleDao implements IRoleDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findRoleUser(Roles role) {
        Query<Role> query = sessionFactory.getCurrentSession().createQuery("from Role where name = :rolename");
        query.setParameter("rolename", role);
        return query.getSingleResult();
    }
}
