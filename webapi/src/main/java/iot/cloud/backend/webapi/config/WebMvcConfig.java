package iot.cloud.backend.webapi.config;

import jakarta.annotation.Resource;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author weichuang
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer, HandlerInterceptor {
    @Resource
    TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CorsInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }

    @Bean
    public FilterRegistrationBean<RequestCustomFilter> requestCustomFilter() {
        FilterRegistrationBean<RequestCustomFilter> registrationBean = new FilterRegistrationBean<>();
        RequestCustomFilter requestCustomFilter = new RequestCustomFilter();
        registrationBean.setFilter(requestCustomFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}

