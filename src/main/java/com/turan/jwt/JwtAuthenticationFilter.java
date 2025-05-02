package com.turan.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

         String username;
         String token;
         String header;

        header  =  request.getHeader("Authorization");

        if(header==null){
            filterChain.doFilter(request,response);
            return;
        }

         token = header.substring(7);

         try{
             username =  jwtService.getUsernameByToken(token);

             if (username!=null&& SecurityContextHolder.getContext().getAuthentication()==null){
                 UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
                 if (userDetails!=null && jwtService.isExpiredToken(token)){
                     UsernamePasswordAuthenticationToken authentication
                             = new
                             UsernamePasswordAuthenticationToken(username , null , userDetails.getAuthorities());
                      authentication.setDetails(userDetails);

                      SecurityContextHolder.getContext().setAuthentication(authentication);
                 }
             }

         }catch (ExpiredJwtException e){
             System.out.println("Tokeninizin vaxti dolmusdur" + e.getMessage());

         }
         catch (Exception e){
             System.out.println("Umumi bir xeta oldu " + e.getMessage());
         }
         filterChain.doFilter(request,response);
    }
}
