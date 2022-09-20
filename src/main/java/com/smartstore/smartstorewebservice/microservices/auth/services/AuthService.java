package com.smartstore.smartstorewebservice.microservices.auth.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.UserInfo;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final BCryptPasswordEncoder encoder;
    private UserInfoRepository userInfoRepository;

    @Autowired
    public AuthService(UserInfoRepository userInfoRepository) {
        encoder = new BCryptPasswordEncoder();
        this.userInfoRepository = userInfoRepository;
    }

    public UserInfo getUserIfAuthenticated(String username, String password) {
        var user = this.userInfoRepository.findByUsername(username);

        if (encoder.matches(password, user.getPasswordHash()) && user.getActive()) {
            user.setPasswordHash("");
            return user;
        }

        return null;
    }

}
