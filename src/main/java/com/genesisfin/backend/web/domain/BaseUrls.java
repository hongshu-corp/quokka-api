package com.genesisfin.backend.web.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
public class BaseUrls {

    @Value("${packagebutler.baseUrl.logistics}")
    private String logistics;

    @Value("${packagebutler.baseUrl.member}")
    private String member;

    @Value("${packagebutler.baseUrl.order}")
    private String order;

    @Value("${packagebutler.baseUrl.payment}")
    private String payment;

}
