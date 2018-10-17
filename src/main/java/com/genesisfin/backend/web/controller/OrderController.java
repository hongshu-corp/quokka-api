package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.domain.OrderApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.genesisfin.backend.web.domain.Api.sendRequestWithParams;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderApi orderApi;

    @Autowired
    public OrderController(OrderApi orderApi) {
        this.orderApi = orderApi;
    }

    @PostMapping("/orders")
    public ResponseEntity createOrder(@RequestBody String params) {
        return sendRequestWithParams(orderApi::createOrder, params);
    }
}
