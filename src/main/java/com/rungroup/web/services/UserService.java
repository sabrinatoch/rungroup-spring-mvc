package com.rungroup.web.services;

import com.rungroup.web.models.RegistrationDto;
import com.rungroup.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
