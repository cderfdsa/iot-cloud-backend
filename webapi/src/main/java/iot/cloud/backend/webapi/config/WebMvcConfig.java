package iot.cloud.backend.webapi.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author weichuang 2023/5/13 23:06
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/**");
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

