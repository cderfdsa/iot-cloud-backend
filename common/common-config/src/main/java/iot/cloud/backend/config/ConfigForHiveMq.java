package iot.cloud.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weichuang
 */
@ConfigurationProperties(prefix = "hivemq")
@Component
@Data
public class ConfigForHiveMq {
    private AuthManager authManager;

    @Data
    public static class AuthManager {
        private String username;
        private String password;
    }
}
