package com.turan.service.impl;

import com.turan.dto.DtoHuman;
import com.turan.entity.Human;
import com.turan.jwt.AuthRequest;
import com.turan.jwt.AuthResponse;
import com.turan.jwt.JwtService;
import com.turan.repository.HumanRepository;
import com.turan.service.IAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Optional;

@Service
public class AuthServiceImpl implements IAuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);


    @Autowired
    private HumanRepository humanRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtService jwtService;




    /* private RefreshToken createRefreshToken(Human human){
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis()+1000*60*60*4));
        refreshToken.setHuman(human);

        return refreshToken;

    }
*/

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
            UsernamePasswordAuthenticationToken  authenticationToken =
                    new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            authenticationProvider.authenticate(authenticationToken);

                   Optional<Human> optionalHuman = humanRepository.findByUsername(request.getUsername());


                        Human human = optionalHuman.get();
            boolean isPasswordMatch = passwordEncoder.matches(request.getPassword(), human.getPassword());
            if (!isPasswordMatch) {
                throw new RuntimeException("Username or Password is incorrect");
            }


            String token =  jwtService.generateToken(human);


            return new AuthResponse(token);

        } catch (Exception e) {
            throw new RuntimeException("Username or Password is wrong: " + e.getMessage());
        }

    }


}
