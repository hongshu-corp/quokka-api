package com.genesisfin.backend.web.security;

import com.genesisfin.backend.web.domain.MemberApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Value("${packagebutler.baseUrl.member}")
    private String baseUrl;

    @Autowired
    private MemberApi memberApi;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        try {
            Credential credential = memberApi.getCredential(new Credential() {{
                setPhone(phone);
            }}).execute().body();
            return memberFrom(credential);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private UserDetails memberFrom(Credential credential) {
        Member member = new Member(credential.getPhone(), credential.getVerificationCode());
        member.setId(credential.getId());
        return member;
    }
}
