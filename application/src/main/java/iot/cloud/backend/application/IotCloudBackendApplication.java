package iot.cloud.backend.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author weichuang 2023/5/13 19:54
 */
@SpringBootApplication
@ComponentScan(value = "iot.cloud.backend.webapi")
@ComponentScan(value = "iot.cloud.backend.service")
@ComponentScan(value = "iot.cloud.backend.mapper")
@ComponentScan(value = "iot.cloud.backend.config")
@MapperScan(basePackages = {
        "iot.cloud.backend.mapper.modules.*"
})
public class IotCloudBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(IotCloudBackendApplication.class, args);
    }
}
