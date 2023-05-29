package iot.cloud.backend.service.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

/**
 * @author weichuang
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

    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }
}
