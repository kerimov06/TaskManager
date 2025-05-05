package com.turan.service.impl;

import com.turan.entity.Human;
import com.turan.entity.RefreshToken;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.JwtService;
import com.turan.jwt.RefreshTokenRequest;
import com.turan.repository.RefreshTokenRepository;
import com.turan.service.IRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;


@Service
public class RefreshTokenServiceImpl implements IRefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;
    @Autowired
    private JwtService jwtService;

    public boolean isRefreshTokenExpireDate(Date expireDate){
        return new Date().before(expireDate);
    }

    private RefreshToken createRefreshToken(Human human){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setHuman(human);

        return refreshToken;
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(request.getRefreshToken());
        if (optional.isEmpty()){
            System.out.println("Refresh Token Kecerli Deyil" + request.getRefreshToken());
        }
        RefreshToken refreshToken = optional.get();
        if (!isRefreshTokenExpireDate(refreshToken.getExpireDate())){
            System.out.println("Refresh Token Expire Olmusdur" + request.getRefreshToken());
        }

         String accesToken = jwtService.generateToken(refreshToken.getHuman());

          RefreshToken newRefreshToken = createRefreshToken(refreshToken.getHuman());
         RefreshToken savedRefreshToken =  refreshTokenRepository.save(newRefreshToken);

          return  new AuthResponse(accesToken,savedRefreshToken.getRefreshToken());
    }
}
