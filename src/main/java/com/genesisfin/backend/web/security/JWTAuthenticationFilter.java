package com.genesisfin.backend.web.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static java.util.Collections.emptyList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JWTConfig config;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTConfig config){
        this.authenticationManager = authenticationManager;
        this.config = config;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            Credential credential = new ObjectMapper()
                    .readValue(request.getInputStream(), Credential.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credential.getPhone(),
                            credential.getVerificationCode(),
                            emptyList())
            );
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        response.setStatus(401);
        response.addHeader("content-type", "application/json");
        response.getOutputStream().println("{}");
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        response.addHeader(config.getHeaderString(), config.getTokenPrefix() + createToken(authResult));
        response.addHeader("content-type", "application/json");
        response.getOutputStream().println("{}");
    }

    private String createToken(Authentication authResult) {
        Member member = (Member) authResult.getPrincipal();
        return Jwts.builder()
                .setSubject(member.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + config.getExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, config.getSecret().getBytes())
                .claim("id", member.getId())
                .compact();
    }
}
