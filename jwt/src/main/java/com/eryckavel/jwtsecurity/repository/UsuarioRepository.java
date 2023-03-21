package com.eryckavel.jwtsecurity.repository;

import com.eryckavel.jwtsecurity.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.login=:login")
    Optional<Usuario> buscarLogin(String login);

}