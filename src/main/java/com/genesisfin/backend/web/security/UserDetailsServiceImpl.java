package com.genesisfin.backend.web.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Credential credential = new Credential() {{
            setUsername(username);
            setPassword("foofoo");
        }};

        return memberFrom(credential);
    }

    private UserDetails memberFrom(Credential credential) {
        Member member = new Member(credential.getUsername(), credential.getPassword());
        member.setId(credential.getId());
        return member;
    }
}
