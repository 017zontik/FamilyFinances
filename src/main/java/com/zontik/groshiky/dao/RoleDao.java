package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public class RoleDao implements IRoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findRoleUser(String roleName) {
        Query<Role> query = sessionFactory.getCurrentSession().createQuery("from Role where name = :rolename");
        query.setParameter("rolename", roleName);
        return query.getSingleResult();
    }
}
