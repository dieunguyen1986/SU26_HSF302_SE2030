package org.ats.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import org.ats.entities.User;
import org.ats.utils.DbContext;

import java.util.List;

public class UserDaoImpl implements UserDao {
    private EntityManager entityManager;

    public UserDaoImpl() {
        entityManager = DbContext.getEntityManager();
    }

    // INSERT, UPDATE, DELETE --> Transaction
    @Override
    public User createUser(User user) {
        //
        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction(); // chwa khởi tạo
            tx.begin();
            entityManager.persist(user);

            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback(); // NullPointerException
            }
        }

        return user;
    }

    @Override
    public List<User> findAll() {
        // JPQL - Java Persistence Query Language
        TypedQuery<User> typedQuery = entityManager.createQuery("SELECT user FROM User user", User.class);

        return typedQuery.getResultList();
    }
}
