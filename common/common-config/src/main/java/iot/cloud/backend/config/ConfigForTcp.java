package iot.cloud.backend.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author weichuang
 */
@ConfigurationProperties(prefix = "bus")
@Component
@Data
public class ConfigForTcp {
    private Integer port = 15000;
    private Integer bossThread = 1;
}
