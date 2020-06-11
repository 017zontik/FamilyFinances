package com.zontik.groshiky.dao;

import com.zontik.groshiky.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;

@Repository
@Transactional
public class UserDao implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public User findByLogin(String login) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Query<User> findByUsername = session.createQuery("from User where login = :login");
            findByUsername.setParameter("login", login);
            return findByUsername.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }
}