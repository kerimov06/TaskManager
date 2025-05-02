package com.turan.controller;

import com.turan.dto.DtoHuman;
import com.turan.jwt.AuthRequest;
import com.turan.jwt.AuthResponse;

public interface IRestAuthController {

    public DtoHuman register(AuthRequest request);

    public AuthResponse authenticate(AuthRequest request);
}
