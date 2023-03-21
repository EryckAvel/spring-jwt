package com.eryckavel.jwtsecurity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {

    //todo chave secreta
    private static final String SECRET_KEY = "217A25432A46294A404E635266556A586E3272357538782F413F4428472B4B61";

    //todo extrair login do usuario
    public String extrairUsername(String token) {
        return extrairClaims(token, Claims::getSubject);
    }

    //todo extrari expiracao do token
    private Date extrairExpiracao(String token) {
        return extrairClaims(token, Claims::getExpiration);
    }

    //todo metodo para extrari todas as claims
    //todo de forma segura
    public <T> T extrairClaims(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extrairTodasAsClaims(token);
        return claimsResolver.apply(claims);
    }

    //todo medoto para extração de informações dentro privada
    //todo para não dar acesso direto
    private Claims extrairTodasAsClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigniKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    //todo metodo para fazer a desicriptografia
    //todo da SECRET_KEY e transformar em byte para dps virar key
    private Key getSigniKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String gerarToken(UserDetails userDetails){
        return gerarToken(new HashMap<>(), userDetails);
    }

    //TODO Gerar token
    public String gerarToken(Map<String, Objects> extraClaims,
                             UserDetails userDetails){
        return Jwts
                .builder()
                .setClaims(extraClaims) //TODO Informações extras no token
                .setSubject(userDetails.getUsername()) //TODO Passsagem de indentificador
                .setIssuedAt(new Date(System.currentTimeMillis())) //TODO Verificação de validade de tokem da data de emissão
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*24)) //TODO Data de expiração do token
                .signWith(getSigniKey(), SignatureAlgorithm.HS256) //TODO Assinatura com SECRET_KEY
                .compact(); //TODO Finaliza compactando tudo e gerando token
    }

    //TODO Validar token
    public Boolean validarToken(String token, UserDetails userDetails){
        final String username = extrairUsername(token);
        return (username.equals(userDetails.getUsername())) && !tokenExpirado(token);
    }

    //TODO Validar expiração de token
    private boolean tokenExpirado(String token) {
        return extrairExpiracao(token).before(new Date());
    }



}
