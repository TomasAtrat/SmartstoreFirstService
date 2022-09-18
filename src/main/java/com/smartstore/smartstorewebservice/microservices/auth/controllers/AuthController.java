package com.smartstore.smartstorewebservice.microservices.auth.controllers;

import com.smartstore.smartstorewebservice.dataAccess.entities.UserInfo;
import com.smartstore.smartstorewebservice.microservices.auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @GetMapping("/")
    public UserInfo getUser(@RequestBody UserInfo user){
        return this.authService.getUserIfAuthenticated(user);
    }

}
