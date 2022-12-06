package com.springboot.tokenauthenticate.controller;

import com.springboot.tokenauthenticate.service.UserService;
import com.springboot.tokenauthenticate.domain.dto.UserLoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(){
        String token=userService.login("", "");
        return ResponseEntity.ok().body(new UserLoginResponse(token));
    }
}
