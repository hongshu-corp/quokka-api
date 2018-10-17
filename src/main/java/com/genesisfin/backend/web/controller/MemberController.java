package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.domain.MemberApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.genesisfin.backend.web.domain.Api.sendRequestWithParams;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberApi memberApi;

    @Autowired
    public MemberController(MemberApi memberApi) {
        this.memberApi = memberApi;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody String params) {
        return sendRequestWithParams(memberApi::register, params);
    }

    @PostMapping("/sendcode")
    public ResponseEntity sendCode(@RequestBody String params) {
        return sendRequestWithParams(memberApi::sendCode, params);
    }
}
