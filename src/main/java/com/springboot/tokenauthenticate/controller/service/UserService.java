package com.springboot.tokenauthenticate.controller.service;

import com.springboot.tokenauthenticate.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Value("${jwt.token.secret}")
    private String secretKey;

    private long expireTimeMs=1000 * 60 * 60;
    public String login(String username, String password) {
        return JwtTokenUtil.createToken(username, secretKey, expireTimeMs);
    }
}
