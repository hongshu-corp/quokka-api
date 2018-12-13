package com.genesisfin.backend.web.repository;

import com.genesisfin.backend.web.model.AuthCode;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCodeRepository extends IRepository<AuthCode, Long> {
    Optional<AuthCode> findAuthCodeByKeyCode(String keyCode);
}