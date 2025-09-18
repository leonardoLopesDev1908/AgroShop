package com.dailycodework.agroshop.controller.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dailycodework.agroshop.request.LoginRequest;
import com.dailycodework.agroshop.security.jwt.JwtUtils;
import com.dailycodework.agroshop.security.user.ShopUserDetailsService;
import com.dailycodework.agroshop.utils.CookieUtils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/vi/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final JwtUtils jwtUtils;
    private final CookieUtils cookieUtils;
    private final ShopUserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManger; 


    public ResponseEntity<?> authenticatioUsuario(LoginRequest request, HttpServletResponse response){
        Authentication authentication = authenticationManger
                    .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));
        String accessToken = jwtUtils.generateAccessTo(authentication);
        String refreshToken = jwtUtils.
    }

}
