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
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
          UsernamePasswordAuthenticationToken auth = new
                  UsernamePasswordAuthenticationToken(request.getPassword(),request.getUsername());
              authenticationProvider.authenticate(auth);
              Optional<Human> optional = humanRepository.findByUsername(request.getUsername());
              String token = jwtService.generateToken(optional.get());

              return new AuthResponse(token);


      }catch (Exception e){
          System.out.println("Username or Password is wrong ");

      }
      return null;
    }
}
