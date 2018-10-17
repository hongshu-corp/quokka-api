package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.domain.LogisticsApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.genesisfin.backend.web.domain.Api.sendRequestWithParams;

@RestController
@RequestMapping("/logistics")
public class LogisticsController {

    private final LogisticsApi logisticsApi;

    public LogisticsController(LogisticsApi logisticsApi) {
        this.logisticsApi = logisticsApi;
    }

    @PostMapping("/rates")
    public ResponseEntity getShippingRates(@RequestBody String request) {
        return sendRequestWithParams(logisticsApi::rates, request);
    }

}
