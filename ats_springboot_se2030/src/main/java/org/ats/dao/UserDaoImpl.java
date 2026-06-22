package org.ats.dao;

import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.ats.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class UserDaoImpl implements UserDao {
    private final SessionFactory sessionFactory;

    // INSERT, UPDATE, DELETE --> Transaction
    @Override
    public User createUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);

        return user;
    }

    @Override
    public List<User> findAll() {
        // JPQL - Java Persistence Query Language
        TypedQuery<User> typedQuery = sessionFactory.getCurrentSession().createQuery("SELECT user FROM User user", User.class);

        return typedQuery.getResultList();
    }
}
