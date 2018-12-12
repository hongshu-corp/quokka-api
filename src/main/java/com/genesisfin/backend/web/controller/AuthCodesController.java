package com.genesisfin.backend.web.controller;

import com.genesisfin.backend.web.model.AuthCode;
import com.genesisfin.backend.web.repository.AuthCodeRepository;
import com.google.code.kaptcha.Producer;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/code")
@AllArgsConstructor
@Slf4j
public class AuthCodesController {

    private static final String PREFIX = "AUTH_";

    private final Producer producer;
    private final AuthCodeRepository repository;

    @GetMapping
    public byte[] index(@RequestParam(value = "random", defaultValue = "") String random) throws Exception {
        String code = producer.createText();
        BufferedImage image = producer.createImage(code);

        removeOutdatedItems();

        if (Strings.isNullOrEmpty(random) || random.length() < 6) {
            throw new Exception("invalid operation");
        }

        AuthCode authCode = new AuthCode();
        authCode.setCode(code).setKeyCode(PREFIX + random);
        repository.save(authCode);

        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpeg", os);
        } catch (IOException e) {
            log.error("ImageIO write err", e);
            throw e;
        }

        return os.toByteArray();
    }

    private void removeOutdatedItems() {
        List<AuthCode> codes = repository.findAll();
        for (AuthCode code : codes) {
            long span = Time.now() - code.getCreatedTime().getTime();
            if (TimeUnit.MILLISECONDS.toMinutes(span) > 5) {
                repository.delete(code);
            }
        }
    }

}