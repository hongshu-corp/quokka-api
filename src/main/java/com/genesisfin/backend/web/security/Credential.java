package com.genesisfin.backend.web.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credential {

    private long id;
    private String username;
    private String password;
    private String authCode;
    private String random;

}
