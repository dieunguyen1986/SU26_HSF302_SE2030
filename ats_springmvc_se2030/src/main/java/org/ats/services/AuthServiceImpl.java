package org.ats.services;

import lombok.RequiredArgsConstructor;
import org.ats.dao.AuthDao;
import org.ats.dto.UserRequest;
import org.ats.entities.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements  AuthService {
    private final AuthDao authDao;

    @Override
    public User authenticate(UserRequest request) {
        return authDao.login(request);
    }
}
