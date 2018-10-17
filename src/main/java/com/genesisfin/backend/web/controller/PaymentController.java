package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.domain.PaymentApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.genesisfin.backend.web.domain.Api.sendRequestWithId;
import static com.genesisfin.backend.web.domain.Api.sendRequestWithIdAndParams;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentApi paymentApi;

    @Autowired
    public PaymentController(PaymentApi paymentApi) {
        this.paymentApi = paymentApi;
    }

    @PostMapping("/members/{id}/accounts")
    public ResponseEntity createPaymentAccount(@PathVariable("id") String id, @RequestBody String params) {
        return sendRequestWithIdAndParams(paymentApi::createPaymentAccount, id, params);
    }

    @GetMapping("/members/{id}/accounts")
    public ResponseEntity getPaymentAccounts(@PathVariable("id") String id) {
        return sendRequestWithId(paymentApi::getPaymentAccounts, id);
    }

}
