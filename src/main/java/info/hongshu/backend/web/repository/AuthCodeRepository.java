package info.hongshu.backend.web.repository;

import info.hongshu.backend.web.model.AuthCode;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCodeRepository extends IRepository<AuthCode, Long> {
    Optional<AuthCode> findAuthCodeByKeyCode(String keyCode);
}