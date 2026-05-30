package org.ats.dao;

import org.ats.entities.User;

import java.util.List;

public interface UserDao {
    User createUser(User user);

    List<User> findAll();
}
