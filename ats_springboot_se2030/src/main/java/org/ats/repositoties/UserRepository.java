package org.ats.repositoties;

import org.ats.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPasswordHash(String email, String passwordHash);
}
