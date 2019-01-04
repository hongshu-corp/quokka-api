package info.hongshu.backend.web.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JWTConfig {
    @Value(value = "${security.jwt.expirationTime}")
    private long expirationTime;
    @Value(value = "${security.jwt.secret}")
    private String secret;
    @Value(value = "${security.jwt.headerString}")
    private String headerString;
    @Value(value = "${security.jwt.tokenPrefix}")
    private String tokenPrefix;
}
