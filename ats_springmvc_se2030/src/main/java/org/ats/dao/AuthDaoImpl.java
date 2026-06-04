package org.ats.dao;

import lombok.RequiredArgsConstructor;
import org.ats.dto.UserRequest;
import org.ats.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AuthDaoImpl implements  AuthDao {
    private final SessionFactory sessionFactory;

    @Override
    public User login(UserRequest userRequest) {
        Session session = sessionFactory.openSession();
        // HQL
        Query<User> query = session.createQuery("SELECT u FROM User u " +
                "WHERE u.email = :email AND u.passwordHash = :password");

        query.setParameter("email", userRequest.getEmail());
        query.setParameter("password", userRequest.getPasswordHash());

        return query.getSingleResult();
    }
}
