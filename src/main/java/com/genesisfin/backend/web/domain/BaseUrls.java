package com.genesisfin.backend.web.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class BaseUrls {

    @Value("${genesisfin.baseUrl.logistics}")
    private String logistics;

    @Value("${genesisfin.baseUrl.member}")
    private String member;

    @Value("${genesisfin.baseUrl.order}")
    private String order;

    @Value("${genesisfin.baseUrl.payment}")
    private String payment;

}
