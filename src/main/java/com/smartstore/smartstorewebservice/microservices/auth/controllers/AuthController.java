package com.smartstore.smartstorewebservice.microservices.auth.controllers;

import com.smartstore.smartstorewebservice.dataAccess.entities.UserInfo;
import com.smartstore.smartstorewebservice.microservices.auth.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @GetMapping("/username={username}&password={password}")
    public UserInfo getUser(@PathVariable String username, @PathVariable String password){
        return this.authService.getUserIfAuthenticated(username, password);
    }

}
