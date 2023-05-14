package iot.cloud.backend.webapi.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Collection;
import java.util.Enumeration;

/**
 * @author weichuang 2023/5/13 22:50
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {

    private static final ThreadLocal<Long> threadLocalForTime = new ThreadLocal<>();


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        threadLocalForTime.set(System.currentTimeMillis());
        //
        log.info("Request uri = [{}], method is: [{}]", request.getRequestURI(), request.getMethod());
        log.info("Request header is : [{}]", parseRequestHeaders(request));
        log.info("Request param is : [{}]", parseRequestParams(request));
        //
        if (request instanceof RequestCustomWrapper) {
            RequestCustomWrapper requestCustomWrapper = (RequestCustomWrapper) request;
            byte[] body = requestCustomWrapper.getBody();
            log.info("Request body is : [{}]", new String(body));
        }
        //
        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Response uri = [{}], method is: [{}]", request.getRequestURI(), request.getMethod());
        log.info("Response header is : [{}]", parseResponseHeaders(response));
//        log.info("Response param is : [{}]", parseParams(request));
        long timems = System.currentTimeMillis() - threadLocalForTime.get();
        log.info("request -> response time(ms) = {}", timems);
    }

    private String parseRequestParams(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            request.getParameter(name);
            stringBuilder.append(name).append("=").append(";");
        }
        return stringBuilder.toString();
    }

    private String parseRequestHeaders(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            stringBuilder.append(name).append("=").append(value).append(";");
        }
        return stringBuilder.toString();
    }


    private String parseResponseHeaders(HttpServletResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        Collection<String> headerNames = response.getHeaderNames();
        headerNames.stream().forEach((String name) -> {
            String value = response.getHeader(name);
            stringBuilder.append(name).append("=").append(value).append(";");
        });
        return stringBuilder.toString();
    }

}

