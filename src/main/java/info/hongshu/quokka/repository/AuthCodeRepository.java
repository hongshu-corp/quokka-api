package info.hongshu.quokka.repository;

import info.hongshu.quokka.model.AuthCode;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthCodeRepository extends IRepository<AuthCode, Long> {
    Optional<AuthCode> findAuthCodeByKeyCode(String keyCode);
}