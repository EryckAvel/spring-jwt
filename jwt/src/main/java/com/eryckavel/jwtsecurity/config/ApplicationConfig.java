package com.eryckavel.jwtsecurity.config;

import com.eryckavel.jwtsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
public class ApplicationConfig {

    @Autowired
    UsuarioRepository repository;


    //TODO Forma de implementção do UserDetailsService via Bean
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> repository.buscarLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrdo!"));
    }

}
