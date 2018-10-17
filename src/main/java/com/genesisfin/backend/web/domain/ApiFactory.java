package com.genesisfin.backend.web.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.genesisfin.backend.web.domain.Api.createApi;

@Configuration
public class ApiFactory {

    @Autowired
    private BaseUrls baseUrls;

    @Bean
    public LogisticsApi createLogisticsApi() {
        return createApi(LogisticsApi.class, baseUrls.getLogistics());
    }

    @Bean
    public MemberApi createMemberApi() {
        return createApi(MemberApi.class, baseUrls.getMember());
    }

    @Bean
    public OrderApi createOrderApi() {
        return createApi(OrderApi.class, baseUrls.getOrder());
    }

    @Bean
    public PaymentApi createPaymentApi() {
        return createApi(PaymentApi.class, baseUrls.getPayment());
    }
}
