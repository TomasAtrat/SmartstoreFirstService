package com.smartstore.smartstorewebservice.microservices.preparation.services.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.UserInfo;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserInfoRepository userInfoRepository;

    public UserService(UserInfoRepository userInfoRepository){
        this.userInfoRepository = userInfoRepository;
    }

    public Optional<UserInfo> getUserById(Long id){
        return this.userInfoRepository.findById(id);
    }
}
