package iot.cloud.backend.application;

import iot.cloud.backend.mqtt.MqttServerUtils;
import iot.cloud.backend.service.utils.SpringApplicationUtils;
import iot.cloud.backend.tcp.TcpServerUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.integration.annotation.IntegrationComponentScan;

/**
 * @author weichuang
 */
@SpringBootApplication
@ComponentScan(value = "iot.cloud.backend.webapi")
@ComponentScan(value = "iot.cloud.backend.service")
@ComponentScan(value = "iot.cloud.backend.mapper")
@ComponentScan(value = "iot.cloud.backend.config")
@ComponentScan(value = "iot.cloud.backend.mqtt")
@ComponentScan(value = "iot.cloud.backend.tcp")
@IntegrationComponentScan(value = "iot.cloud.backend.service")
@MapperScan(basePackages = {
        "iot.cloud.backend.mapper.modules.*"
})
@EnableCaching
public class IotCloudBackendApplication {

    public static void main(String[] args) {
        //
        MqttServerUtils.start();
        //
        SpringApplication springApplication = new SpringApplication(IotCloudBackendApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        SpringApplicationUtils.setSpringApplication(springApplication);
        ApplicationContext applicationContext = springApplication.run(args);
        SpringApplicationUtils.setApplicationContext(applicationContext);
        //
        TcpServerUtils.start();
    }
}
