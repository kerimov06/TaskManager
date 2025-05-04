package com.turan.service.impl;

import com.turan.dto.DtoHuman;
import com.turan.entity.Human;
import com.turan.entity.RefreshToken;
import com.turan.jwt.AuthRequest;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.JwtService;
import com.turan.repository.HumanRepository;
import com.turan.repository.RefreshTokenRepository;
import com.turan.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements IAuthService {

    @Autowired
    private HumanRepository humanRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;



     private RefreshToken createRefreshToken(Human human){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setHuman(human);

        return refreshToken;

    }


    @Override
    public DtoHuman register(AuthRequest request) {

        DtoHuman dto = new DtoHuman();

        Human human = new Human();
         human.setUsername(request.getUsername());
         human.setPassword(passwordEncoder.encode(request.getPassword()));

         Human savedHuman = humanRepository.save(human);
         BeanUtils.copyProperties(savedHuman,dto);

        return dto;
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        try {
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            authenticationProvider.authenticate(auth);

            Human human = humanRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            String accessToken = jwtService.generateToken(human);

            RefreshToken refreshToken = createRefreshToken(human);
            refreshTokenRepository.save(refreshToken);

            return new AuthResponse(accessToken, refreshToken.getRefreshToken());

        } catch (Exception e) {
            throw new RuntimeException("Username or Password is wrong");
        }
    }

}
