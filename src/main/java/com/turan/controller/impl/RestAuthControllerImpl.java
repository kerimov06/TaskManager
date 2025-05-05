package com.turan.controller.impl;

import com.turan.controller.IRestAuthController;
import com.turan.dto.DtoHuman;
import com.turan.jwt.AuthRequest;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.RefreshTokenRequest;
import com.turan.service.IAuthService;
import com.turan.service.IRefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestAuthControllerImpl implements IRestAuthController {

     @Autowired
    private IAuthService authService;

     @Autowired
     private IRefreshTokenService refreshTokenService;


    @PostMapping("/register")
    @Override
    public DtoHuman register(@Valid @RequestBody AuthRequest request) {
        return  authService.register(request);
    }

    @PostMapping("/authenticate")
    @Override
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }

    @PostMapping("/refreshToken")
    @Override
    public AuthResponse refreshToken(@RequestBody RefreshTokenRequest request) {
        return refreshTokenService.refreshToken(request);
    }
}
