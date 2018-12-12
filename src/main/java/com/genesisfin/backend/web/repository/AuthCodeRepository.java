package com.genesisfin.backend.web.repository;

import com.genesisfin.backend.web.model.AuthCode;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthCodeRepository extends IRepository<AuthCode, Long> {
}