package com.eryckavel.jwtsecurity.controller;

import com.eryckavel.jwtsecurity.dto.LoginDTO;
import com.eryckavel.jwtsecurity.dto.TokenDTO;
import com.eryckavel.jwtsecurity.dto.UsuarioDTO;
import com.eryckavel.jwtsecurity.model.Usuario;
import com.eryckavel.jwtsecurity.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> login(@RequestBody UsuarioDTO dto){
        return service.cadastro(dto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody LoginDTO dto){
        return service.login(dto);
    }

}
