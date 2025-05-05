package com.turan.service;

import com.turan.jwt.AuthResponse;
import com.turan.jwt.RefreshTokenRequest;

public interface IRefreshTokenService {

    public AuthResponse refreshToken(RefreshTokenRequest request);

}
