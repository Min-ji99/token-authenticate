package com.springboot.tokenauthenticate.configuration;

import com.springboot.tokenauthenticate.service.UserService;
import com.springboot.tokenauthenticate.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.http.HttpHeaders;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {
    private final UserService userService;
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader=request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authorizationHeader : {}", authorizationHeader);
        if(authorizationHeader==null || !authorizationHeader.startsWith("Bearer ")){
            log.error("헤더를 가져오는 과정에서 에러 발생");
            filterChain.doFilter(request, response);
        }
        String token;
        try{
            token=authorizationHeader.split(" ")[1].trim();
        }catch(Exception e){
            log.error("token 추출 실패");
            filterChain.doFilter(request, response);
            return;
        }

        if(JwtTokenUtil.isExpired(token, secretKey)){
            log.error("유효시간 만료");
            filterChain.doFilter(request, response);
            return;
        }

    }
}