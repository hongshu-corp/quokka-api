package com.genesisfin.backend.web.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Collections.emptyList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final JWTConfig config;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTConfig config) {
        super(authenticationManager);
        this.config = config;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(config.getHeaderString());

        if (header == null || !header.startsWith(config.getTokenPrefix())) {
            chain.doFilter(request, response);
            return;
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationOf(request));
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken authenticationOf(HttpServletRequest request) {
        String token = request.getHeader(config.getHeaderString());
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(config.getSecret().getBytes())
                    .parseClaimsJws(token.replace(config.getTokenPrefix(), ""))
                    .getBody()
                    .getSubject();
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, emptyList());
            }
        }
        return null;
    }
}
