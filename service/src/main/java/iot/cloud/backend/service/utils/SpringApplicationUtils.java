package iot.cloud.backend.service.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author weichuang 2023/5/20 23:30
 */
public class SpringApplicationUtils {
    private static SpringApplication springApplication;
    private static ApplicationContext applicationContext;

    public static void setSpringApplication(SpringApplication aspringApplication) {
        springApplication = aspringApplication;
    }

    public static SpringApplication getSpringApplication() {
        return springApplication;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationUtils.applicationContext = applicationContext;
    }
}
