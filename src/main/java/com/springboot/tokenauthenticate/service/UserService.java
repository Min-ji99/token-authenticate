package com.springboot.tokenauthenticate.service;

import com.springboot.tokenauthenticate.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Value("${jwt.token.secret}")
    private String secretKey;

    private long expireTimeMs=1000 * 60 * 60;
    public String login(String username, String password) {
        return JwtTokenUtil.createToken("minji", secretKey, expireTimeMs);
    }
}
