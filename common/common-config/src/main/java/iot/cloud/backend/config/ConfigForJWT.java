package iot.cloud.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weichuang 2023/5/14 18:23
 */
@ConfigurationProperties(prefix = "jwt")
@Component
@Data
public class ConfigForJWT {
    private String secret;
    private Integer expHours;
}
