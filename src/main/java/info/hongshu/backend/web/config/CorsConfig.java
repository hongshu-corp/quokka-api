package info.hongshu.backend.web.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties("security")
@Getter
@Setter
public class CorsConfig {

    private List<String> allowedOrigins;
}
