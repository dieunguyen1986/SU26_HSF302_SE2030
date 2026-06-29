package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.AuthDao;
import org.ats.dto.UserRequest;
import org.ats.entities.User;
import org.ats.repositoties.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements  AuthService {
    private final UserRepository userRepository;

    @Override
    public User authenticate(UserRequest request) {
        User user = userRepository.findByEmailAndPasswordHash(request.getEmail(), request.getPasswordHash());
        return user;
    }
}
