package iot.cloud.backend.application;

import io.github.quickmsg.starter.config.EnableMqttServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author weichuang
 */
@SpringBootApplication
@ComponentScan(value = "iot.cloud.backend.webapi")
@ComponentScan(value = "iot.cloud.backend.service")
@ComponentScan(value = "iot.cloud.backend.mapper")
@ComponentScan(value = "iot.cloud.backend.config")
@MapperScan(basePackages = {
        "iot.cloud.backend.mapper.modules.*"
})
@EnableMqttServer
public class IotCloudBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotCloudBackendApplication.class, args);
    }
}
