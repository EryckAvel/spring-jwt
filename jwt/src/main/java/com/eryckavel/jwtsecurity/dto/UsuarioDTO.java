package com.eryckavel.jwtsecurity.dto;

public class UsuarioDTO {

    private String nomecompleto;
    private String email;
    private String login;
    private String senha;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String nomecompleto, String email, String login, String senha) {
        this.nomecompleto = nomecompleto;
        this.email = email;
        this.login = login;
        this.senha = senha;
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
