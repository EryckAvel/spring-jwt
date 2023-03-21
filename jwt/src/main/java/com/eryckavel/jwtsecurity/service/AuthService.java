package com.eryckavel.jwtsecurity.service;

import com.eryckavel.jwtsecurity.dto.LoginDTO;
import com.eryckavel.jwtsecurity.dto.TokenDTO;
import com.eryckavel.jwtsecurity.dto.UsuarioDTO;
import com.eryckavel.jwtsecurity.model.Usuario;
import com.eryckavel.jwtsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    JwtService jwt;

    public ResponseEntity<TokenDTO> cadastro(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        copiarDTOparaEntidade(usuario, dto);
        usuario = repository.save(usuario);
        var token = jwt.gerarToken(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new TokenDTO(token));
    }

    public TokenDTO login(LoginDTO dto) {
        return null;
    }

    private void copiarDTOparaEntidade(Usuario entidade, UsuarioDTO dto) {
        entidade.setNomecompleto(dto.getNomecompleto());
        entidade.setEmail(dto.getEmail());
        entidade.setLogin(dto.getLogin());
        entidade.setSenha(dto.getSenha());
    }

}
