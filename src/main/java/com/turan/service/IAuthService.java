package com.turan.service;

import com.turan.dto.DtoHuman;
import com.turan.jwt.AuthRequest;
import com.turan.jwt.AuthResponse;

public interface IAuthService {

     public DtoHuman register(AuthRequest request);

     public AuthResponse authenticate(AuthRequest request);
}
