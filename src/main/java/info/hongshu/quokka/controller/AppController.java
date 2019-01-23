package info.hongshu.quokka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/ping")
    public String ping() {
        return "OK";
    }
}
