package com.eryckavel.jwtsecurity.model;

import jakarta.persistence.*;
import lombok.Builder;

@Builder
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome_completo")
    private String nomecompleto;
    @Column(name = "email", length = 80)
    private String email;
    @Column(name = "login", unique = true, nullable = false, length = 50)
    private String login;
    @Column(name = "senha", nullable = false, length = 80)
    private String senha;

    public Usuario() {
    }

    public Usuario(Long id, String nomecompleto, String email, String login, String senha) {
        this.id = id;
        this.nomecompleto = nomecompleto;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomecompleto() {
        return nomecompleto;
    }

    public void setNomecompleto(String nomecompleto) {
        this.nomecompleto = nomecompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
