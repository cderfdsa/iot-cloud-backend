package iot.cloud.backend.webapi.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * @author weichuang
 */
public class RequestCustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            String contentType = servletRequest.getContentType();
            if (contentType != null && contentType.contains("application/json")) {
                ServletRequest requestWrapper = new RequestCustomWrapper((HttpServletRequest) servletRequest);
                filterChain.doFilter(requestWrapper, servletResponse);
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

