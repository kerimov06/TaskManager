package com.turan.config;

import com.turan.entity.Human;
import com.turan.repository.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.swing.text.html.Option;
import java.util.Optional;

@Configuration
public class AppConfig {


    @Autowired
    private HumanRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService(){
         return new UserDetailsService() {
             @Override
             public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                   Optional<Human> optional =  userRepository.findByUserName(username);
                     if (optional.isPresent()){
                          return optional.get();
                     }
                 return null;
             }
         };
    }



   @Bean
    public AuthenticationProvider authenticationProvider(){
       DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
           authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
           authenticationProvider.setUserDetailsService(userDetailsService());

           return authenticationProvider;

   }

   @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
   }
}
