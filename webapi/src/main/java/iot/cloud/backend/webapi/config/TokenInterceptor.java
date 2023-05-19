package iot.cloud.backend.webapi.config;

import iot.cloud.backend.common.utils.JWTUtils;
import iot.cloud.backend.common.utils.StringUtils;
import iot.cloud.backend.common.utils.exception.InvalidateTokenException;
import iot.cloud.backend.config.ConfigForJWT;
import iot.cloud.backend.service.utils.UserUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author weichuang
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    ConfigForJWT configForJWT;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //
        if (request.getRequestURI().contains("/ns/")) {
            return true;
        }
        //
        String headerToken = request.getHeader("token");
        String bodyToken = "";
        //
        if (StringUtils.isNotEmpty(headerToken)) {
            Long userId = JWTUtils.getUserId(headerToken, configForJWT.getSecret());
        } else {
            if (request instanceof RequestCustomWrapper) {
                RequestCustomWrapper requestCustomWrapper = (RequestCustomWrapper) request;
                bodyToken = requestCustomWrapper.getToken();
                if (StringUtils.isNotEmpty(bodyToken)) {
                    Long userId = JWTUtils.getUserId(bodyToken, configForJWT.getSecret());
                    UserUtils.saveUserId(userId);
                } else {
                    throw new InvalidateTokenException();
                }
            } else {
                throw new InvalidateTokenException();
            }
        }
        return true;
    }
}
