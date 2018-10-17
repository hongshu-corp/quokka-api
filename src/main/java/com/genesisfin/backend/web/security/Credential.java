package com.genesisfin.backend.web.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credential {

    private long id;
    private String phone;
    private String verificationCode;

}
